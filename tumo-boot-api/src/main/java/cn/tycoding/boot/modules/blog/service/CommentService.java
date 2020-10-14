package cn.tycoding.boot.modules.blog.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.blog.entity.Comment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 评论表(Comment)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:50:20
 */
public interface CommentService extends IService<Comment> {

    /**
     * 条件查询
     */
    List<Comment> list(Comment comment);

    /**
     * 分页、条件查询
     */
    IPage<Comment> list(Comment comment, QueryPage queryPage);

    /**
     * 新增
     */
    void add(Comment comment);

    /**
     * 修改
     */
    void update(Comment comment);

    /**
     * 删除
     */
    void delete(Long id);
}
