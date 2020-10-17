package cn.tycoding.boot.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签表(Tag)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:50:44
 */
@Data
@TableName("blog_tag")
@ApiModel(value = "标签表实体")
public class Tag implements Serializable {
    private static final long serialVersionUID = -53368783671633008L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "标签ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String name;

    /**
     * 色彩
     */
    @ApiModelProperty(value = "色彩")
    private String color;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
