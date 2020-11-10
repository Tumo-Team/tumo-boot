package cn.tycoding.boot.modules.auth.component;

import cn.tycoding.boot.common.core.api.TumoHttpStatus;
import cn.tycoding.boot.modules.auth.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

/**
 * 重写OAuth2异常
 *
 * @author tycoding
 * @since 2020/10/16
 */
@Component
public class TumoWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    /**
     * @author tycoding
     * @see org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator
     * @since 2020/10/16
     */
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(e);

        // 请求未授权
        Exception ase = (AuthenticationException) this.throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception(new TumoUnauthorizedException(e.getMessage(), e));
        }

        // 拒绝访问异常
        ase = (AccessDeniedException) this.throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception(new TumoForbiddenException(ase.getMessage(), ase));
        }

        // Token失效异常
        ase = (InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new TumoInvalidGrantException(ase.getMessage(), ase));
        }

        // 请求方法不支持异常
        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new TumoMethodNotAllowedException(ase.getMessage(), ase));
        }

        // OAuth2Exception异常
        ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception((OAuth2Exception) ase);
        }

        return handleOAuth2Exception(new TumoServerErrorException(TumoHttpStatus.INTERNAL_SERVER_ERROR.getMsg(), e));
    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {
        int code = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (code == HttpStatus.UNAUTHORIZED.value() || e instanceof InsufficientScopeException) {
            headers.set("WWW-Authenticate", String.format("%s %s", "Bearer", e.getSummary()));
        }

        return new ResponseEntity<>(new TumoOAuth2Exception(e.getOAuth2ErrorCode(), code), headers,
                HttpStatus.valueOf(TumoHttpStatus.SUCCESS.getCode()));
    }
}
