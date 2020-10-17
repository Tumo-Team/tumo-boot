package cn.tycoding.boot.common.props;

import lombok.Data;

import java.util.List;

/**
 * Swagger Package Group分组配置
 *
 * @author tycoding
 * @since 2020/10/17
 */
@Data
public class SwaggerPackageProperties {

    /**
     * Package GroupName
     */
    private String name;

    /**
     * Package Group下所有的URL
     */
    private List<String> url;
}
