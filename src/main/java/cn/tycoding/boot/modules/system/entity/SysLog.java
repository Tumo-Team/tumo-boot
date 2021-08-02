package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表(Log)实体类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@TableName("sys_log")
@Accessors(chain = true)
@ApiModel(value = "系统日志表实体")
public class SysLog implements Serializable {
    private static final long serialVersionUID = -39039111282732175L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 操作用户
     */
    @ApiModelProperty(value = "操作用户")
    private String username;

    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    private Integer type;

    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述")
    private String operation;

    /**
     * 请求URL
     */
    @ApiModelProperty(value = "请求URL")
    private String url;

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
     * 用户代理
     */
    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private Date createTime;
}
