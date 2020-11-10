package cn.tycoding.boot.modules.blog.service.impl;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.blog.entity.Comment;
import cn.tycoding.boot.modules.blog.mapper.CommentMapper;
import cn.tycoding.boot.modules.blog.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:50:20
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<Comment> list(Comment comment) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Comment> list(Comment comment, QueryPage queryPage) {
        IPage<Comment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(comment.getContent()), Comment::getContent, comment.getContent());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Comment comment) {
        comment.setCreateTime(new Date());
        baseMapper.insert(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Comment comment) {
        baseMapper.updateById(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
