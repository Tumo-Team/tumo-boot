package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
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
     * 条件查询
     */
    List<Role> list(Role role);

    /**
     * 分页、条件查询
     */
    IPage<Role> list(Role role, QueryPage queryPage);

    /**
     * 新增
     */
    void add(Role role);

    /**
     * 修改
     */
    void update(Role role);

    /**
     * 删除
     */
    void delete(Long id);
}
