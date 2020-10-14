package cn.tycoding.boot.modules.setting.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.setting.entity.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统日志表(Log)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 16:53:42
 */
public interface LogService extends IService<Log> {

    /**
     * 条件查询
     */
    List<Log> list(Log log);

    /**
     * 分页、条件查询
     */
    IPage<Log> list(Log log, QueryPage queryPage);

    /**
     * 新增
     */
    void add(Log log);

    /**
     * 修改
     */
    void update(Log log);

    /**
     * 删除
     */
    void delete(Long id);
}
