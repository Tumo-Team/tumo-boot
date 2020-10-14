package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dao.SysArticleDao;
import cn.tycoding.boot.modules.system.entity.SysArticle;
import cn.tycoding.boot.modules.system.service.SysArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章表(SysArticle)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-13 11:11:28
 */
@Service
@AllArgsConstructor
public class SysArticleServiceImpl extends ServiceImpl<SysArticleDao, SysArticle> implements SysArticleService {

    private final SysArticleDao sysArticleDao;

    @Override
    public List<SysArticle> list(SysArticle sysArticle) {
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysArticle> list(SysArticle sysArticle, QueryPage queryPage) {
        IPage<SysArticle> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank(sysArticle.getName()), SysArticle::getName, sysArticle.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysArticle sysArticle) {
        baseMapper.insert(sysArticle);
    }

    @Override
    @Transactional
    public void update(SysArticle sysArticle) {
        baseMapper.updateById(sysArticle);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
