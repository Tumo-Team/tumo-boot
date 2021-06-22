package cn.tycoding.boot.modules.auth.component;

import cn.tycoding.boot.modules.auth.exception.TumoAuth2Exception;
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
public class TumoAuth2ExceptionSerializer extends StdSerializer<TumoAuth2Exception> {
    private static final long serialVersionUID = 2241320471807860252L;

    protected TumoAuth2ExceptionSerializer() {
        super(TumoAuth2Exception.class);
    }

    @Override
    public void serialize(TumoAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", e.getHttpErrorCode());
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeStringField("data", null);
        jsonGenerator.writeEndObject();
    }
}
