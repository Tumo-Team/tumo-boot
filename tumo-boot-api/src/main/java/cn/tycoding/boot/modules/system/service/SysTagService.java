package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.SysTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 标签表(SysTag)表服务接口
 *
 * @author tycoding
 * @since 2020-10-13 11:13:26
 */
public interface SysTagService extends IService<SysTag> {

    /**
     * 条件查询
     */
    List<SysTag> list(SysTag sysTag);

    /**
     * 分页、条件查询
     */
    IPage<SysTag> list(SysTag sysTag, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysTag sysTag);

    /**
     * 修改
     */
    void update(SysTag sysTag);

    /**
     * 删除
     */
    void delete(Long id);
}
