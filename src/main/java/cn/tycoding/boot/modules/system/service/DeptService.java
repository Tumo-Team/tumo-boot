package cn.tycoding.boot.modules.system.service;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.modules.system.entity.Dept;
import cn.tycoding.boot.modules.system.entity.Menu;
import cn.tycoding.boot.modules.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表(Dept)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:47:25
 */
public interface DeptService extends IService<Dept> {

    /**
     * 条件查询
     */
    List<Dept> list(Dept dept);

    /**
     * 获取部门Tree
     */
    List<Tree<Object>> tree();

    /**
     * 获取所属用户列表
     */
    List<User> userList(Long id);

    /**
     * 校验名称是否存在
     */
    boolean checkName(Dept dept);

    /**
     * 新增
     */
    void add(Dept dept);

    /**
     * 修改
     */
    void update(Dept dept);

    /**
     * 删除
     */
    void delete(Long id);

}
