package cn.tycoding.boot.modules.upms.service;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.modules.upms.entity.SysMenu;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:45:23
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户ID查询其关联的所有角色
     */
    List<SysRole> findRolesByUserId(Long id);

    /**
     * 条件查询
     */
    List<SysRole> list(SysRole sysRole);

    /**
     * 获取角色Tree集合
     */
    List<Tree<Object>> tree();

    /**
     * 此接口将获取角色表中id、name、ids等基础数据
     */
    Dict baseTree();

    /**
     * 获取指定角色ID下的所有菜单权限
     */
    List<SysMenu> getMenuListByRoleId(Long id);

    /**
     * 获取所属用户列表
     */
    List<SysUser> userList(Long id);

    /**
     * 校验名称是否存在
     */
    boolean checkName(SysRole sysRole);

    /**
     * 新增
     */
    void add(SysRole sysRole);

    /**
     * 分配权限
     */
    void addPermission(List<Long> permissionList, Long id);

    /**
     * 修改
     */
    void update(SysRole sysRole);

    /**
     * 删除
     */
    void delete(Long id);

}
