package cn.tycoding.boot.common.log.exception;

import cn.tycoding.boot.common.core.api.HttpCode;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author tycoding
 * @since 2021/6/10
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -1068765335343416833L;

    @Getter
    private final HttpCode httpCode;

    public ServiceException(String message) {
        super(message);
        this.httpCode = HttpCode.FAILURE;
    }

    public ServiceException(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

}
