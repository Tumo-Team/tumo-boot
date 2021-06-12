package cn.tycoding.boot.common.redis.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 对Redis中Token信息的扩展封装
 *
 * @author tycoding
 * @see org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
 * @since 2021/6/12
 */
@Data
@ApiModel(value = "Token信息实体")
public class TokenInfo implements Serializable {
    private static final long serialVersionUID = -1331900349298552602L;

    @ApiModelProperty(value = "Token值")
    private String value;

    @ApiModelProperty(value = "过期时间")
    private Date expiration;

    @ApiModelProperty(value = "Token类型")
    private String tokenType;

    @ApiModelProperty(value = "刷新Token")
    private OAuth2RefreshToken refreshToken;

    @ApiModelProperty(value = "Scope")
    private Set<String> scope;

    @ApiModelProperty("用户信息")
    private Object principal;
}
