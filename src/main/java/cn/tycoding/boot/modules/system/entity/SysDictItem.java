package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典项表(SysDictItem)实体类
 *
 * @author TyCoding
 * @since 2021-08-06
 */
@Data
@TableName("sys_dict_item")
public class SysDictItem implements Serializable {
    private static final long serialVersionUID = 859322231277654399L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典表主键
     */
    private Long dictId;

    /**
     * 字典值
     */
    private String value;

    /**
     * 显示名称
     */
    private String label;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String des;

    /**
     * 是否是系统内置
     */
    private Boolean isSystem;

}

