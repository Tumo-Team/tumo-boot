package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.modules.auth.component.TumoAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author tycoding
 * @since 2021/5/21
 */
@JsonSerialize(using = TumoAuth2ExceptionSerializer.class)
public class TumoOAuth2Exception extends OAuth2Exception {

    @Getter
    private int code;

    public TumoOAuth2Exception(String msg) {
        super(msg);
    }

    public TumoOAuth2Exception(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public TumoOAuth2Exception(String msg, Throwable t, int code) {
        super(msg, t);
        this.code = code;
    }
}
