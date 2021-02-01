package cn.tycoding.boot.modules.upms.service;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.upms.dto.UserDTO;
import cn.tycoding.boot.modules.upms.entity.Role;
import cn.tycoding.boot.modules.upms.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:32:27
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询
     */
    User findByName(String username);

    /**
     * 根据ID查询
     */
    UserDTO findById(Long id);

    /**
     * 根据用户名封装：用户信息、角色、部门、权限
     */
    UserInfo info(String username);

    /**
     * 根据用户ID查询角色
     */
    List<Role> roleList(Long id);

    /**
     * 条件查询
     */
    List<User> list(User user);

    /**
     * 分页、条件查询
     */
    IPage<UserDTO> list(UserDTO user, QueryPage queryPage);

    /**
     * 校验用户名是否存在
     */
    boolean checkName(User user);

    /**
     * 新增
     */
    void add(UserDTO user);

    /**
     * 分配角色
     */
    void addRole(List<Long> roleList, Long id);

    /**
     * 修改
     */
    void update(UserDTO user);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 重置密码
     */
    void resetPass(User user);
}
