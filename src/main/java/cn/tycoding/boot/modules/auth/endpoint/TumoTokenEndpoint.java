package cn.tycoding.boot.modules.auth.endpoint;

import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义令牌端点
 *
 * @author tycoding
 * @since 2020/10/18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_AUTH_PREFIX)
public class TumoTokenEndpoint {

    private final TokenStore tokenStore;

    /**
     * 注销登录并清除Token
     *
     * @param authHeader 请求头
     * @return 结果
     */
    @DeleteMapping("/logout")
    public R<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isEmpty(authHeader)) {
            return new R<>();
        }
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(authHeader);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
            return new R<>();
        }
        // 清空access_token
        tokenStore.removeAccessToken(accessToken);
        // 清空refresh_token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        return new R<>();
    }
}
