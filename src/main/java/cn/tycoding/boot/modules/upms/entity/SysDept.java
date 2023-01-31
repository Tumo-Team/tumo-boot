package cn.tycoding.boot.modules.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表(Dept)实体类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@TableName("sys_dept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = -94917153262781949L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 上级部门ID
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 描述
     */
    private String des;
}
