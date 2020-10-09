package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dao.SysUserDao;
import cn.tycoding.boot.modules.system.entity.SysUser;
import cn.tycoding.boot.modules.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-09 16:37:46
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    private final SysUserDao sysUserDao;

    @Override
    public List<SysUser> list(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysUser> list(SysUser sysUser, QueryPage queryPage) {
        IPage<SysUser> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank(sysUser.getName()), SysUser::getName, sysUser.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysUser sysUser) {
        baseMapper.insert(sysUser);
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        baseMapper.updateById(sysUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
