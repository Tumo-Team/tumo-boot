package cn.tycoding.boot.modules.blog.mapper;

import cn.tycoding.boot.modules.blog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:50:26
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
