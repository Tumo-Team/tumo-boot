
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


## Hutool

如果使用`TreeUtil`工具类，那么需要注意：

```java
    public static <E> List<Tree<E>> build(List<TreeNode<E>> list, E parentId) {
        return build(list, parentId, TreeNodeConfig.DEFAULT_CONFIG, new DefaultNodeParser());
    }
```

这里的`parentId`是你调用此静态方法时传递的`parent_id`字段标识（一般我们设置为0），这里传入的类型一定要和实体类中`parentId`属性类型相同。
例如你调用`TreeUtil.build(list, 0)`，那么实体类中`parentId`属性应该是`Integer`类型；
如果你调用`TreeUtil.build(list, 0L)`,而实体类中`parentId`字段还是`Integer`类型，此工具类就无法构建Tree，必须设置为`Long parentId`类型才可。
