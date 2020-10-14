package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表(SysArticle)实体类
 *
 * @author tycoding
 * @since 2020-10-13 11:11:26
 */
@Data
@TableName("sys_article")
public class SysArticle implements Serializable {
    private static final long serialVersionUID = -19749261152289832L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介
     */
    private String introduce;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章阅读量
     */
    private Long eyes;

    /**
     * 创建时间
     */
    private Date createTime;

}
