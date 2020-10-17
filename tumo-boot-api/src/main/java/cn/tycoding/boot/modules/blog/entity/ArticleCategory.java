package cn.tycoding.boot.modules.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类关联表(ArticleCategory)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:48:30
 */
@Data
@TableName("blog_article_category")
public class ArticleCategory implements Serializable {
    private static final long serialVersionUID = -86626259789460504L;

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
     * 分类ID
     */
    private Long categoryId;

}
