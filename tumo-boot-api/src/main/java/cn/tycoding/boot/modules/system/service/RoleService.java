package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dto.RoleWithMenu;
import cn.tycoding.boot.modules.system.entity.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:45:23
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID查询其关联的所有角色
     */
    List<Role> findRolesByUserId(Long id);

    /**
     * 条件查询
     */
    List<Role> list(Role role);

    /**
     * 分页、条件查询
     */
    IPage<Role> list(Role role, QueryPage queryPage);

    /**
     * 校验名称是否存在
     */
    boolean checkName(Role role);

    /**
     * 新增
     */
    void add(RoleWithMenu roleWithMenu);

    /**
     * 修改
     */
    void update(RoleWithMenu roleWithMenu);

    /**
     * 删除
     */
    void delete(Long id);

}
