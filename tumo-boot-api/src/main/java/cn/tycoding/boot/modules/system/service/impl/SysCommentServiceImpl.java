package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dao.SysCommentDao;
import cn.tycoding.boot.modules.system.entity.SysComment;
import cn.tycoding.boot.modules.system.service.SysCommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 评论表(SysComment)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-13 11:13:01
 */
@Service
@AllArgsConstructor
public class SysCommentServiceImpl extends ServiceImpl<SysCommentDao, SysComment> implements SysCommentService {

    private final SysCommentDao sysCommentDao;

    @Override
    public List<SysComment> list(SysComment sysComment) {
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysComment> list(SysComment sysComment, QueryPage queryPage) {
        IPage<SysComment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank(sysComment.getName()), SysComment::getName, sysComment.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysComment sysComment) {
        baseMapper.insert(sysComment);
    }

    @Override
    @Transactional
    public void update(SysComment sysComment) {
        baseMapper.updateById(sysComment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
