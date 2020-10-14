package cn.tycoding.boot.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表(SysComment)实体类
 *
 * @author tycoding
 * @since 2020-10-13 11:12:59
 */
@Data
@TableName("sys_comment")
public class SysComment implements Serializable {
    private static final long serialVersionUID = -71178454150228410L;

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
     * 文章标题
     */
    private Long articleTitle;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 评论人名称
     */
    private String name;

    /**
     * 评论人邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

}
