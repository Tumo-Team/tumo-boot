package cn.tycoding.boot.modules.auth.component;

import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.modules.auth.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

/**
 * 重写Oauth2异常处理器
 *
 * @author tycoding
 * @since 2021/5/21
 */
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
            return this.handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
        }

        // 拒绝访问异常
        ase = (AccessDeniedException) this.throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
        }

        // Token失效异常
        ase = (org.springframework.security.oauth2.common.exceptions.InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(org.springframework.security.oauth2.common.exceptions.InvalidGrantException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new InvalidGrantException(ase.getMessage(), ase));
        }

        // 请求方法不支持异常
        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new MethodNotAllowedException(ase.getMessage(), ase));
        }

        // OAuth2Exception异常
        ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception((OAuth2Exception) ase);
        }

        return handleOAuth2Exception(new ServerErrorException(HttpCode.INTERNAL_SERVER_ERROR.getMsg(), e));
    }

    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {
        int code = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (code == HttpStatus.UNAUTHORIZED.value() || e instanceof InsufficientScopeException) {
            headers.set("WWW-Authenticate", String.format("%s %s", "Bearer", e.getSummary()));
        }
        TumoAuth2Exception auth2Exception = new TumoAuth2Exception(e.getMessage(), e.getCause(), code);
        return new ResponseEntity<>(auth2Exception, headers, HttpStatus.valueOf(code));
    }

    private static class UnauthorizedException extends TumoAuth2Exception {

        public UnauthorizedException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return HttpCode.UN_AUTHORIZED.getMsg();
        }

        @Override
        public int getHttpErrorCode() {
            return HttpCode.UN_AUTHORIZED.getCode();
        }
    }

    private static class ForbiddenException extends TumoAuth2Exception {

        public ForbiddenException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return HttpCode.FORBIDDEN.getMsg();
        }

        @Override
        public int getHttpErrorCode() {
            return HttpCode.FORBIDDEN.getCode();
        }
    }

    private static class InvalidGrantException extends TumoAuth2Exception {

        public InvalidGrantException(String msg, Throwable e) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return HttpCode.INVALID_GRANT.getMsg();
        }

        @Override
        public int getHttpErrorCode() {
            return HttpCode.INVALID_GRANT.getCode();
        }
    }

    private static class ServerErrorException extends TumoAuth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return HttpCode.INTERNAL_SERVER_ERROR.getMsg();
        }

        @Override
        public int getHttpErrorCode() {
            return HttpCode.INTERNAL_SERVER_ERROR.getCode();
        }
    }

    private static class MethodNotAllowedException extends TumoAuth2Exception {

        public MethodNotAllowedException(String msg, Throwable e) {
            super(msg);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return HttpCode.METHOD_NOT_SUPPORTED.getMsg();
        }

        @Override
        public int getHttpErrorCode() {
            return HttpCode.METHOD_NOT_SUPPORTED.getCode();
        }
    }
}
