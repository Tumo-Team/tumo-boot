package cn.tycoding.boot.common.oss.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本地文件上传配置
 *
 * @author tycoding
 * @since 2021/6/3
 */
@Data
@ConfigurationProperties("tumo-boot.file")
public class LocalFileProperties {

    /**
     * 文件上传地址
     */
    private String uploadPath = System.getProperty("user.dir") + "/target/classes/static/upload";

    /**
     * 文件访问地址
     */
    private String remotePath = "http://127.0.0.1:8010/upload";
}
