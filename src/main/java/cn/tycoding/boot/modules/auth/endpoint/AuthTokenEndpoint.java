package cn.tycoding.boot.modules.auth.endpoint;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.core.constant.CaptchaConstant;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.CacheConstant;
import cn.tycoding.boot.common.core.utils.BeanUtil;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.common.redis.utils.RedisUtil;
import cn.tycoding.boot.common.redis.utils.TokenInfo;
import cn.tycoding.boot.modules.auth.dto.TumoUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

/**
 * 自定义授权相关的接口
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_AUTH_PREFIX)
public class AuthTokenEndpoint {

    private final TokenStore tokenStore;
    private final RedisTemplate redisTemplate;
    private final CacheManager cacheManager;

    /**
     * 获取验证码
     */
    @GetMapping("/captcha")
    public R<Dict> getCaptcha() {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(CaptchaConstant.CAPTCHA_WIDTH, CaptchaConstant.CAPTCHA_HEIGHT, CaptchaConstant.CAPTCHA_COUNT, CaptchaConstant.CAPTCHA_CIRCLE_COUNT);
        String code = captcha.getCode().toLowerCase();
        String key = RedisUtil.getKey();
        redisTemplate.opsForValue().set(CacheConstant.CAPTCHA_PREFIX + key, code, Duration.ofMinutes(CaptchaConstant.CAPTCHA_TIMEOUT));
        return R.ok(Dict.create().set("key", key).set("image", captcha.getImageBase64()));
    }

    /**
     * 注销登录并清除Token
     */
    @DeleteMapping("/logout")
    public R<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        clear(AuthUtil.getToken(token));
        return R.ok();
    }

    private void clear(String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
            return;
        }
        OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
        // 清空access_token
        tokenStore.removeAccessToken(accessToken);
        // 清空refresh_token
        tokenStore.removeRefreshToken(accessToken.getRefreshToken());

        // 清空User Details
        cacheManager.getCache(CacheConstant.USER_DETAIL_KEY).evict(authentication.getName());
        // 清空Menu Details
        TumoUser principal = (TumoUser) authentication.getPrincipal();
        cacheManager.getCache(CacheConstant.MENU_DETAIL_KEY).evict(principal.getId());
    }

    /**
     * 强制下线
     */
    @DeleteMapping("/token/{token}")
    @PreAuthorize("@auth.hasAuth('system:token:delete')")
    public R tokenDel(@PathVariable String token) {
        clear(token);
        return R.ok();
    }

    /**
     * 分页获取在线Token
     */
    @GetMapping("/token/page")
    public R tokenPage(QueryPage queryPage) {
        String key = String.format("%sauth_to_access:*", CacheConstant.OAUTH_PREFIX);
        List<String> keysPage = RedisUtil.getKeysPage(redisTemplate, key, 1, 10);
        List<DefaultOAuth2AccessToken> list = redisTemplate.opsForValue().multiGet(keysPage);
        List<TokenInfo> tokenInfoList = BeanUtil.copy(list, TokenInfo.class);

        tokenInfoList.forEach(info -> {
            OAuth2Authentication authentication = tokenStore.readAuthentication(info.getValue());
            info.setPrincipal(authentication.getPrincipal());
        });

        int total = redisTemplate.keys(key).size();
        Page page = new Page(queryPage.getPage(), queryPage.getLimit(), total);
        page.setRecords(tokenInfoList);
        return R.ok(MybatisUtil.getData(page));
    }
}
