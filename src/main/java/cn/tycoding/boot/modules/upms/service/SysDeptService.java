package cn.tycoding.boot.modules.upms.service;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.modules.upms.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表(Dept)表服务接口
 *
 * @author tycoding
 * @since 2021/5/21
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 条件查询
     */
    List<SysDept> list(SysDept sysDept);

    /**
     * 获取部门Tree
     */
    List<Tree<Object>> tree();

    /**
     * 删除
     */
    void delete(Long id);

}
