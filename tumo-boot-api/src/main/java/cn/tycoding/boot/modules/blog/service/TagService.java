package cn.tycoding.boot.modules.blog.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.blog.entity.Article;
import cn.tycoding.boot.modules.blog.entity.Tag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 标签表(Tag)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:50:45
 */
public interface TagService extends IService<Tag> {

    /**
     * 条件查询
     */
    List<Tag> list(Tag tag);

    /**
     * 分页、条件查询
     */
    IPage<Tag> list(Tag tag, QueryPage queryPage);

    /**
     * 查询相关的文章列表
     */
    IPage<Article> articleList(QueryPage queryPage, Long id);

    /**
     * 校验名称是否存在
     */
    boolean checkName(Tag tag);

    /**
     * 新增
     */
    void add(Tag tag);

    /**
     * 修改
     */
    void update(Tag tag);

    /**
     * 删除
     */
    void delete(Long id);

}
