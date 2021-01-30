package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.modules.upms.entity.Dept;
import cn.tycoding.boot.modules.upms.entity.User;
import cn.tycoding.boot.modules.upms.mapper.DeptMapper;
import cn.tycoding.boot.modules.upms.mapper.UserMapper;
import cn.tycoding.boot.modules.upms.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:47:26
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    private final UserMapper userMapper;

    @Override
    public List<Dept> list(Dept dept) {
        return baseMapper.selectList(new LambdaQueryWrapper<Dept>().like(Dept::getName, dept.getName()));
    }

    @Override
    public List<Tree<Object>> tree() {
        List<Dept> deptList = this.list(new Dept());

        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        deptList.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId(),
                    t.getParentId(),
                    t.getName(),
                    0
            );
            node.setExtra(Dict.create().set("des", t.getDes()).set("createTime", t.getCreateTime()));
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    public List<User> userList(Long id) {
        return userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getDeptId, id).select(
                User::getId,
                User::getUsername,
                User::getAvatar,
                User::getSex,
                User::getPhone,
                User::getEmail,
                User::getStatus
        ));
    }

    @Override
    public boolean checkName(Dept dept) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<Dept>().eq(Dept::getName, dept.getName());
        if (dept.getId() != null && dept.getId() != 0) {
            queryWrapper.ne(Dept::getId, dept.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Dept dept) {
        if (dept.getParentId() == null) {
            dept.setParentId(0L);
        }
        dept.setCreateTime(new Date());
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
