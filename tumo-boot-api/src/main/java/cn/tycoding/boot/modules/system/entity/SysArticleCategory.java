package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类关联表(SysArticleCategory)实体类
 *
 * @author tycoding
 * @since 2020-10-13 11:11:54
 */
@Data
@TableName("sys_article_category")
public class SysArticleCategory implements Serializable {
    private static final long serialVersionUID = -49245966704177193L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
