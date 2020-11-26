package cn.tycoding.boot.common.log.exception;

import cn.tycoding.boot.common.core.api.HttpCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tycoding
 * @since 2020/11/23
 */
@Slf4j
public class ServiceException extends RuntimeException {

    @Setter
    @Getter
    private HttpCode httpCode;

    public ServiceException(String message) {
        super(message);
        this.httpCode = HttpCode.FAILURE;
    }

    public ServiceException(HttpCode httpCode) {
        super(httpCode.getMsg());
        this.httpCode = httpCode;
    }

    public ServiceException(HttpCode httpCode, Throwable cause) {
        super(cause);
        this.httpCode = httpCode;
    }
}
