package cn.tycoding.boot.modules.dao;

import cn.tycoding.boot.modules.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-09 14:55:17
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}
