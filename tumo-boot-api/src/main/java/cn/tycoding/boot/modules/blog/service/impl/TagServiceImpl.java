package cn.tycoding.boot.modules.blog.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.blog.entity.Article;
import cn.tycoding.boot.modules.blog.entity.Tag;
import cn.tycoding.boot.modules.blog.mapper.TagMapper;
import cn.tycoding.boot.modules.blog.service.TagService;
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
 * 标签表(Tag)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:50:45
 */
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> list(Tag tag) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Tag> list(Tag tag, QueryPage queryPage) {
        IPage<Tag> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(tag.getName()), Tag::getName, tag.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Article> articleList(QueryPage queryPage, Long id) {
        IPage<Article> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return baseMapper.articleList(page, id);
    }

    @Override
    public boolean checkName(Tag tag) {
        if (StringUtils.isBlank(tag.getName())) {
            return false;
        }
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        if (tag.getId() != null && tag.getId() != 0) {
            queryWrapper.eq(Tag::getName, tag.getName());
            queryWrapper.ne(Tag::getId, tag.getId());
        } else {
            queryWrapper.eq(Tag::getName, tag.getName());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Tag tag) {
        tag.setCreateTime(new Date());
        baseMapper.insert(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Tag tag) {
        baseMapper.updateById(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
