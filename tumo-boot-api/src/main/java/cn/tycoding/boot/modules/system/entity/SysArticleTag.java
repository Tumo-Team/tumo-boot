package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签关联表(SysArticleTag)实体类
 *
 * @author tycoding
 * @since 2020-10-13 11:12:08
 */
@Data
@TableName("sys_article_tag")
public class SysArticleTag implements Serializable {
    private static final long serialVersionUID = 961976304760940621L;

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
     * 标签ID
     */
    private Long tagId;

}
