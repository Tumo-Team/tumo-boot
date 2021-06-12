package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.common.log.exception.ServiceException;
import cn.tycoding.boot.modules.upms.entity.SysDept;
import cn.tycoding.boot.modules.upms.mapper.SysDeptMapper;
import cn.tycoding.boot.modules.upms.service.SysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDept> list(SysDept sysDept) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysDept>()
                .orderByAsc(SysDept::getOrderNo)
                .like(StringUtils.isNotEmpty(sysDept.getName()), SysDept::getName, sysDept.getName()));
    }

    @Override
    public List<Tree<Object>> tree() {
        List<SysDept> sysDeptList = this.list();

        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        sysDeptList.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId(),
                    t.getParentId(),
                    t.getName(),
                    0
            );
            node.setExtra(Dict.create().set("orderNo", t.getOrderNo()).set("des", t.getDes()));
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        List<SysDept> list = baseMapper.selectList(new LambdaQueryWrapper<SysDept>().eq(SysDept::getParentId, id));
        if (list.size() > 0) {
            throw new ServiceException("该部门包含子节点，不能删除");
        }
        baseMapper.deleteById(id);
    }
}
