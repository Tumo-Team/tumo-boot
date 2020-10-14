package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签表(SysTag)实体类
 *
 * @author tycoding
 * @since 2020-10-13 11:13:25
 */
@Data
@TableName("sys_tag")
public class SysTag implements Serializable {
    private static final long serialVersionUID = 310604303695392354L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 色彩
     */
    private String color;

    /**
     * 创建时间
     */
    private Date createTime;

}
