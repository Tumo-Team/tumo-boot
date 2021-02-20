package cn.tycoding.boot.modules.upms.mapper;

import cn.tycoding.boot.modules.upms.dto.SysUserDTO;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(User)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:32:32
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<SysUserDTO> list(IPage<SysUser> page, SysUserDTO user, Long ignoreId);
}
