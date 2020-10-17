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
 * 分类表(Category)实体类
 *
 * @author tycoding
 * @since 2020-10-14 14:49:14
 */
@Data
@TableName("blog_category")
@ApiModel(value = "分类表实体")
public class Category implements Serializable {
    private static final long serialVersionUID = -44234316664016814L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 分类描述
     */
    @ApiModelProperty(value = "分类描述")
    private String des;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
