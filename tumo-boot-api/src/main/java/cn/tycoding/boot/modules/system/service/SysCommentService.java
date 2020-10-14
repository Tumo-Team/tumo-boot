package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.SysComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 评论表(SysComment)表服务接口
 *
 * @author tycoding
 * @since 2020-10-13 11:13:00
 */
public interface SysCommentService extends IService<SysComment> {

    /**
     * 条件查询
     */
    List<SysComment> list(SysComment sysComment);

    /**
     * 分页、条件查询
     */
    IPage<SysComment> list(SysComment sysComment, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysComment sysComment);

    /**
     * 修改
     */
    void update(SysComment sysComment);

    /**
     * 删除
     */
    void delete(Long id);
}
