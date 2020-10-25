package cn.tycoding.boot.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表(Comment)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:50:19
 */
@Data
@TableName("blog_comment")
@ApiModel(value = "评论表实体")
public class Comment implements Serializable {
    private static final long serialVersionUID = -40844815323943146L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文章ID
     */
    @ApiModelProperty(value = "文章ID")
    private Long articleId;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private Long articleTitle;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Long pid;

    /**
     * 评论人名称
     */
    @ApiModelProperty(value = "评论人名称")
    private String name;

    /**
     * 评论人邮箱
     */
    @ApiModelProperty(value = "评论人邮箱")
    private String email;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
