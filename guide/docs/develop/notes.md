
SpringSecurity 默认用户名密码在 org.springframework.boot.autoconfigure.security.SecurityProperties 中定义

## 自定义OAuth Token端点地址

`AuthorizationServerConfigurerAdapter`提供了修改endpoint默认端点接口地址的方法：

```java
@Override
public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
        .pathMapping("/oauth/token", "/tumo-boot/auth/oauth/token");
}
```

## 重写Security OAuth 异常机制

1. 继承`OAuth2Exception`

```java
@JsonSerialize(using = TumoOAuth2ExceptionSerializer.class)
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
}
```

2. 继承`StdSerializer<>`，重写异常返回格式

```java
public class TumoOAuth2ExceptionSerializer extends StdSerializer<TumoOAuth2Exception> {

    protected TumoOAuth2ExceptionSerializer() {
        super(TumoOAuth2Exception.class);
    }

    /**
     * 自定义封装异常返回值数据结构
     *
     * @author tycoding
     * @see org.springframework.security.oauth2.common.exceptions.OAuth2ExceptionJackson2Serializer
     * @since 2020/10/16
     */
    @Override
    public void serialize(TumoOAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", e.getCode());
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeStringField("data", null);
        jsonGenerator.writeEndObject();
    }
}
```
