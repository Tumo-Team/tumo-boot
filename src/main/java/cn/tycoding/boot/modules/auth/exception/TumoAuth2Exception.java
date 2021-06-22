package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.modules.auth.component.TumoAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 重写 OAuth2 异常类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@JsonSerialize(using = TumoAuth2ExceptionSerializer.class)
public class TumoAuth2Exception extends OAuth2Exception {

    @Getter
    private int code;

    public TumoAuth2Exception(String msg) {
        super(msg);
    }

    public TumoAuth2Exception(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public TumoAuth2Exception(String msg, Throwable t, int code) {
        super(msg, t);
        this.code = code;
    }
}
