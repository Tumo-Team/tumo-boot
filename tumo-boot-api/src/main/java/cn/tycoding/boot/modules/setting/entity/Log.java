package cn.tycoding.boot.modules.setting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表(Log)实体类
 *
 * @author tycoding
 * @since 2020-10-14 16:53:40
 */
@Data
@TableName("sys_log")
@ApiModel(value = "系统日志表实体")
public class Log implements Serializable {
    private static final long serialVersionUID = -39039111282732175L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作用户
     */
    @ApiModelProperty(value = "操作用户")
    private String username;

    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述")
    private String operation;

    /**
     * 耗时(毫秒)
     */
    @ApiModelProperty(value = "耗时(毫秒)")
    private Long time;

    /**
     * 操作方法
     */
    @ApiModelProperty(value = "操作方法")
    private String method;

    /**
     * 操作参数
     */
    @ApiModelProperty(value = "操作参数")
    private String params;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String ip;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private Date createTime;

    /**
     * 操作地点
     */
    @ApiModelProperty(value = "操作地点")
    private String location;

}
