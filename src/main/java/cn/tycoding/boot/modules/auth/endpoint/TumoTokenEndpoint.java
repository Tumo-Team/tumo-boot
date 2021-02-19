package cn.tycoding.boot.modules.auth.endpoint;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.auth.constant.CaptchaConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.CacheConstant;
import cn.tycoding.boot.common.redis.config.TumoRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

/**
 * 自定义令牌端点
 *
 * @author tycoding
 * @since 2020/10/18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_AUTH_PREFIX)
@Api(value = "Token端点接口", tags = "Token端点接口")
public class TumoTokenEndpoint {

    private final TokenStore tokenStore;
    private final TumoRedis tumoRedis;

    /**
     * 获取验证码
     */
    @GetMapping("/captcha")
    @ApiOperation(value = "获取验证码")
    public R<Dict> getCaptcha() {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(CaptchaConstant.CAPTCHA_WIDTH, CaptchaConstant.CAPTCHA_HEIGHT, CaptchaConstant.CAPTCHA_COUNT, CaptchaConstant.CAPTCHA_CIRCLE_COUNT);
        String code = captcha.getCode().toLowerCase();
        String key = UUID.randomUUID().toString();
        tumoRedis.set(CacheConstant.CAPTCHA_REDIS_KEY + key, code, Duration.ofMinutes(CaptchaConstant.CAPTCHA_TIMEOUT));
        return R.data(Dict.create().set("key", key).set("image", captcha.getImageBase64()));
    }

    /**
     * 注销登录并清除Token
     */
    @DeleteMapping("/logout")
    @ApiOperation(value = "注销接口")
    public R<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isEmpty(authHeader)) {
            return R.ok();
        }
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(authHeader);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
            return R.ok();
        }
        // 清空access_token
        tokenStore.removeAccessToken(accessToken);
        // 清空refresh_token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        return R.ok();
    }
}
