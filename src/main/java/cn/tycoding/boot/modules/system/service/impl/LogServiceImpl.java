package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.Log;
import cn.tycoding.boot.modules.system.mapper.LogMapper;
import cn.tycoding.boot.modules.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志表(Log)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 16:53:44
 */
@Service
@RequiredArgsConstructor
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public List<Log> list(Log log) {
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Log> list(Log log, QueryPage queryPage) {
        IPage<Log> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Log log) {
        baseMapper.insert(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Log log) {
        baseMapper.updateById(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
