package cn.tycoding.boot.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签关联表(ArticleTag)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:48:46
 */
@Data
@TableName("blog_article_tag")
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 922601515359066121L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 标签ID
     */
    private Long tagId;

}
