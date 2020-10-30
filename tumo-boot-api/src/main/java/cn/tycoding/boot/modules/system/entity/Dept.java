package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门表(Dept)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:47:25
 */
@Data
@TableName("sys_dept")
@ApiModel(value = "部门表实体")
public class Dept implements Serializable {
    private static final long serialVersionUID = -94917153262781949L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "部门ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级部门ID
     */
    @ApiModelProperty(value = "上级部门ID")
    private Long parentId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @NotNull(message = "部门名称不能为空")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String des;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
