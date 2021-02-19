package cn.tycoding.boot.modules.system.mapper;

import cn.tycoding.boot.modules.system.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志表(Log)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 16:53:54
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
