package cn.tycoding.boot.modules.upms.service;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.modules.upms.dto.SysRoleDTO;
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
     * 获取角色Tree集合
     */
    List<Tree<Object>> tree(SysRole sysRole);

    /**
     * 获取所属用户列表
     */
    List<SysUser> userList(Long id);

    /**
     * 校验名称是否存在
     */
    boolean checkName(SysRole sysRole);

    /**
     * 根据ID查询
     */
    SysRoleDTO findById(Long roleId);

    /**
     * 新增角色
     */
    void add(SysRoleDTO sysRole);

    /**
     * 修改角色
     */
    void update(SysRoleDTO sysRole);

    /**
     * 删除
     */
    void delete(Long id);
}
