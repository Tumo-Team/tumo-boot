package cn.tycoding.boot.modules.system.dao;

import cn.tycoding.boot.modules.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(SysCategory)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-13 11:12:40
 */
@Mapper
public interface SysCategoryDao extends BaseMapper<SysCategory> {

}
