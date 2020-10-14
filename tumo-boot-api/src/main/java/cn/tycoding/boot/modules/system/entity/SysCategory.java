package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类表(SysCategory)实体类
 *
 * @author tycoding
 * @since 2020-10-13 11:12:40
 */
@Data
@TableName("sys_category")
public class SysCategory implements Serializable {
    private static final long serialVersionUID = -45444667122539648L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String des;

    /**
     * 创建时间
     */
    private Date createTime;

}
