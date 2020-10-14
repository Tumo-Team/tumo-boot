package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.User;
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
     * 条件查询
     */
    List<User> list(User user);

    /**
     * 分页、条件查询
     */
    IPage<User> list(User user, QueryPage queryPage);

    /**
     * 新增
     */
    void add(User user);

    /**
     * 修改
     */
    void update(User user);

    /**
     * 删除
     */
    void delete(Long id);
}
