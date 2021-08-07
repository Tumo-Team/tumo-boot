package cn.tycoding.boot.modules.system.mapper;

import cn.tycoding.boot.modules.system.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表(SysDict)表数据库访问层
 *
 * @author TyCoding
 * @since 2021-08-06
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

}

