package cn.tycoding.boot.modules.upms.mapper;

import cn.tycoding.boot.modules.upms.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志表(Log)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 16:53:54
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
