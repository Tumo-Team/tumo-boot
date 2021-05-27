package cn.tycoding.boot.modules.resource.mapper;

import cn.tycoding.boot.modules.resource.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志表(Log)表数据库访问层
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
