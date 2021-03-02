package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.SysLog;
import cn.tycoding.boot.modules.system.mapper.SysLogMapper;
import cn.tycoding.boot.modules.system.service.SysLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统日志表(Log)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 16:53:44
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public IPage<SysLog> list(SysLog sysLog, QueryPage queryPage) {
        IPage<SysLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(sysLog.getType() != null, SysLog::getType, sysLog.getType());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysLog sysLog) {
        baseMapper.insert(sysLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
