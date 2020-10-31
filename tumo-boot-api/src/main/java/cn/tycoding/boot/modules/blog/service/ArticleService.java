package cn.tycoding.boot.modules.blog.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.blog.entity.Article;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:48:01
 */
public interface ArticleService extends IService<Article> {

    /**
     * 条件查询
     */
    List<Article> list(Article article);

    /**
     * 分页、条件查询
     */
    IPage<Article> list(Article article, QueryPage queryPage);

    /**
     * 校验名称是否存在
     */
    boolean checkName(Article article);

    /**
     * 新增
     */
    void add(Article article);

    /**
     * 修改
     */
    void update(Article article);

    /**
     * 删除
     */
    void delete(Long id);
}
