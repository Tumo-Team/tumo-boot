package cn.tycoding.boot.modules.system.dao;

import cn.tycoding.boot.modules.system.entity.SysTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签表(SysTag)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-13 11:13:25
 */
@Mapper
public interface SysTagDao extends BaseMapper<SysTag> {

}
