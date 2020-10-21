package cn.tycoding.boot.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表(Article)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:48:01
 */
@Data
@TableName("blog_article")
@ApiModel(value = "文章表实体")
public class Article implements Serializable {
    private static final long serialVersionUID = 896949064757025226L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "文章ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文章作者
     */
    @ApiModelProperty(value = "文章作者")
    private String author;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /**
     * 文章简介
     */
    @ApiModelProperty(value = "文章简介")
    private String introduce;

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String content;

    /**
     * 文章封面
     */
    @ApiModelProperty(value = "文章封面")
    private String cover;

    /**
     * 文章阅读量
     */
    @ApiModelProperty(value = "文章阅读量")
    private Long eyes;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
