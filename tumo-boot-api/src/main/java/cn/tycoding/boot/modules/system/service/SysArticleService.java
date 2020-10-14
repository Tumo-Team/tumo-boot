package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.SysArticle;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 文章表(SysArticle)表服务接口
 *
 * @author tycoding
 * @since 2020-10-13 11:11:27
 */
public interface SysArticleService extends IService<SysArticle> {

    /**
     * 条件查询
     */
    List<SysArticle> list(SysArticle sysArticle);

    /**
     * 分页、条件查询
     */
    IPage<SysArticle> list(SysArticle sysArticle, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysArticle sysArticle);

    /**
     * 修改
     */
    void update(SysArticle sysArticle);

    /**
     * 删除
     */
    void delete(Long id);
}
