package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 分类表(SysCategory)表服务接口
 *
 * @author tycoding
 * @since 2020-10-13 11:12:41
 */
public interface SysCategoryService extends IService<SysCategory> {

    /**
     * 条件查询
     */
    List<SysCategory> list(SysCategory sysCategory);

    /**
     * 分页、条件查询
     */
    IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysCategory sysCategory);

    /**
     * 修改
     */
    void update(SysCategory sysCategory);

    /**
     * 删除
     */
    void delete(Long id);
}
