package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dao.SysCategoryDao;
import cn.tycoding.boot.modules.system.entity.SysCategory;
import cn.tycoding.boot.modules.system.service.SysCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分类表(SysCategory)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-13 11:12:41
 */
@Service
@AllArgsConstructor
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryDao, SysCategory> implements SysCategoryService {

    private final SysCategoryDao sysCategoryDao;

    @Override
    public List<SysCategory> list(SysCategory sysCategory) {
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage) {
        IPage<SysCategory> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank(sysCategory.getName()), SysCategory::getName, sysCategory.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysCategory sysCategory) {
        baseMapper.insert(sysCategory);
    }

    @Override
    @Transactional
    public void update(SysCategory sysCategory) {
        baseMapper.updateById(sysCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
