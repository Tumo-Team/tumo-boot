package cn.tycoding.boot.modules.blog.service.impl;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.auth.utils.SecurityUtil;
import cn.tycoding.boot.modules.blog.entity.Article;
import cn.tycoding.boot.modules.blog.mapper.ArticleMapper;
import cn.tycoding.boot.modules.blog.service.ArticleService;
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
 * 文章表(Article)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:48:02
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> list(Article article) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Article> list(Article article, QueryPage queryPage) {
        IPage<Article> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId,
                Article::getAuthor,
                Article::getTitle,
                Article::getDes,
                Article::getCover,
                Article::getEyes,
                Article::getCreateTime);
        queryWrapper.like(StringUtils.isNotBlank(article.getTitle()), Article::getTitle, article.getTitle());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean checkName(Article article) {
        if (StringUtils.isBlank(article.getTitle())) {
            return false;
        }
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if (article.getId() != null && article.getId() != 0) {
            queryWrapper.eq(Article::getTitle, article.getTitle());
            queryWrapper.ne(Article::getId, article.getId());
        } else {
            queryWrapper.eq(Article::getTitle, article.getTitle());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Article article) {
        article.setCreateTime(new Date());
        article.setAuthor(SecurityUtil.getUsername());
        baseMapper.insert(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Article article) {
        baseMapper.updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
