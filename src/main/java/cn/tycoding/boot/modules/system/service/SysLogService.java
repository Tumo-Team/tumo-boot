package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统日志表(Log)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 16:53:42
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页、条件查询
     */
    IPage<SysLog> list(SysLog sysLog, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysLog sysLog);
    /**
     * 删除
     */
    void delete(Long id);
}
