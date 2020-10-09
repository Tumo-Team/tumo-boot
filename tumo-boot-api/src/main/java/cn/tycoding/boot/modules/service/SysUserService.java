package cn.tycoding.boot.modules.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.entity.SysUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author tycoding
 * @since 2020-10-09 14:55:18
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 条件查询
     */
    List<SysUser> list(SysUser sysUser);

    /**
     * 分页、条件查询
     */
    IPage<SysUser> list(SysUser sysUser, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysUser sysUser);

    /**
     * 修改
     */
    void update(SysUser sysUser);

    /**
     * 删除
     */
    void delete(Long id);
}
