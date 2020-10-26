package cn.tycoding.boot.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.modules.system.dto.DeptDTO;
import cn.tycoding.boot.modules.system.entity.Dept;
import cn.tycoding.boot.modules.system.mapper.DeptMapper;
import cn.tycoding.boot.modules.system.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
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

    @Override
    public List<Dept> list(Dept dept) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
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
            HashMap<String, Object> map = new HashMap<>();
            map.put(DeptDTO.DES_KEY, t.getDes());
            map.put(DeptDTO.CREATE_TIME_KEY, t.getCreateTime());
            node.setExtra(map);
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Dept dept) {
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
