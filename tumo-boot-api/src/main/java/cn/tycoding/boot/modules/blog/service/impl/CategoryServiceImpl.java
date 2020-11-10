package cn.tycoding.boot.modules.blog.service.impl;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.blog.entity.Article;
import cn.tycoding.boot.modules.blog.entity.Category;
import cn.tycoding.boot.modules.blog.mapper.CategoryMapper;
import cn.tycoding.boot.modules.blog.service.CategoryService;
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
 * 分类表(Category)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:49:15
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Category> list(Category category, QueryPage queryPage) {
        IPage<Category> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(category.getName()), Category::getName, category.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Article> articleList(QueryPage queryPage, Long id) {
        IPage<Article> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return baseMapper.articleList(page, id);
    }

    @Override
    public boolean checkName(Category category) {
        if (StringUtils.isBlank(category.getName())) {
            return false;
        }
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        if (category.getId() != null && category.getId() != 0) {
            queryWrapper.eq(Category::getName, category.getName());
            queryWrapper.ne(Category::getId, category.getId());
        } else {
            queryWrapper.eq(Category::getName, category.getName());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Category category) {
        category.setCreateTime(new Date());
        baseMapper.insert(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Category category) {
        baseMapper.updateById(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
