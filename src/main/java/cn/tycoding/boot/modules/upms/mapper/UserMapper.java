package cn.tycoding.boot.modules.upms.mapper;

import cn.tycoding.boot.modules.upms.dto.UserDTO;
import cn.tycoding.boot.modules.upms.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    IPage<UserDTO> list(IPage<User> page, UserDTO user, Long ignoreId);
}
