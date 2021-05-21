package cn.tycoding.boot.modules.auth.component;

import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 自定义Oauth2异常响应格式
 *
 * @author tycoding
 * @see org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Serializer
 * @since 2021/5/21
 */
public class TumoOAuth2ExceptionSerializer extends StdSerializer<TumoOAuth2Exception> {

    protected TumoOAuth2ExceptionSerializer() {
        super(TumoOAuth2Exception.class);
    }

    @Override
    public void serialize(TumoOAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", e.getCode());
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeStringField("data", null);
        jsonGenerator.writeEndObject();
    }
}
