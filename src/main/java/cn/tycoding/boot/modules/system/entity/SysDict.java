package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表(SysDict)实体类
 *
 * @author TyCoding
 * @since 2021-08-06
 */
@Data
@TableName("sys_dict")
public class SysDict implements Serializable {
    private static final long serialVersionUID = -66073261152467734L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String des;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否是系统内置
     */
    private Boolean isSystem;

}

