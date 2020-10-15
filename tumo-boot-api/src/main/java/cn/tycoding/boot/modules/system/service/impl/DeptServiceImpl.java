package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.Dept;
import cn.tycoding.boot.modules.system.mapper.DeptMapper;
import cn.tycoding.boot.modules.system.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:47:26
 */
@Service
@AllArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    private final DeptMapper deptMapper;

    @Override
    public List<Dept> list(Dept dept) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Dept> list(Dept dept, QueryPage queryPage) {
        IPage<Dept> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank(dept.getName()), Dept::getName, dept.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Dept dept) {
        baseMapper.insert(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dept dept) {
        baseMapper.updateById(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
