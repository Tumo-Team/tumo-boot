package cn.tycoding.boot.modules.upms.service;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.modules.upms.entity.SysDept;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表(Dept)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:47:25
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
     * 获取所属用户列表
     */
    List<SysUser> userList(Long id);

    /**
     * 校验名称是否存在
     */
    boolean checkName(SysDept sysDept);

    /**
     * 新增
     */
    void add(SysDept sysDept);

    /**
     * 删除
     */
    void delete(Long id);

}
