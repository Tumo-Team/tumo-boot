package cn.tycoding.boot.modules.oss.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源文件表（OssFile）实体类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@TableName("oss_file")
public class OssFile implements Serializable {
    private static final long serialVersionUID = -7225974227659141556L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件原始名称
     */
    private String originName;

    /**
     * 文件存储名称
     */
    private String targetName;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件描述
     */
    private String des;

    /**
     * 创建时间
     */
    private Date createTime;
}
