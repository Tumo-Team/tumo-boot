package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.modules.upms.entity.SysDept;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.mapper.SysDeptMapper;
import cn.tycoding.boot.modules.upms.mapper.SysUserMapper;
import cn.tycoding.boot.modules.upms.service.SysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysUserMapper sysUserMapper;

    @Override
    public List<SysDept> list(SysDept sysDept) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysDept>().like(sysDept.getName() != null, SysDept::getName, sysDept.getName()));
    }

    @Override
    public List<Tree<Object>> tree() {
        List<SysDept> sysDeptList = this.list();

        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        sysDeptList.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId().toString(),
                    t.getParentId().toString(),
                    t.getName(),
                    0
            );
            node.setExtra(Dict.create().set("des", t.getDes()));
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, "0");
    }

    @Override
    public List<SysUser> userList(Long id) {
        return sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().eq(SysUser::getDeptId, id).select(
                SysUser::getId,
                SysUser::getUsername,
                SysUser::getAvatar,
                SysUser::getSex,
                SysUser::getPhone,
                SysUser::getEmail,
                SysUser::getStatus
        ));
    }

    @Override
    public boolean checkName(SysDept sysDept) {
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<SysDept>().eq(SysDept::getName, sysDept.getName());
        if (sysDept.getId() != null && sysDept.getId() != 0) {
            queryWrapper.ne(SysDept::getId, sysDept.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysDept sysDept) {
        if (sysDept.getParentId() == null) {
            sysDept.setParentId(0L);
        }
        baseMapper.insert(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDept sysDept) {
        baseMapper.updateById(sysDept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        List<SysDept> list = baseMapper.selectList(new LambdaQueryWrapper<SysDept>().eq(SysDept::getParentId, id));
        if (list.size() > 0) {
            throw new RuntimeException("该部门包含子节点，不能删除");
        }
        baseMapper.deleteById(id);
    }
}
