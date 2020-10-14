package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dao.SysTagDao;
import cn.tycoding.boot.modules.system.entity.SysTag;
import cn.tycoding.boot.modules.system.service.SysTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签表(SysTag)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-13 11:13:27
 */
@Service
@AllArgsConstructor
public class SysTagServiceImpl extends ServiceImpl<SysTagDao, SysTag> implements SysTagService {

    private final SysTagDao sysTagDao;

    @Override
    public List<SysTag> list(SysTag sysTag) {
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysTag> list(SysTag sysTag, QueryPage queryPage) {
        IPage<SysTag> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank(sysTag.getName()), SysTag::getName, sysTag.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysTag sysTag) {
        baseMapper.insert(sysTag);
    }

    @Override
    @Transactional
    public void update(SysTag sysTag) {
        baseMapper.updateById(sysTag);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
