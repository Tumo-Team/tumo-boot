package cn.tycoding.boot.modules.blog.mapper;

import cn.tycoding.boot.modules.blog.entity.Article;
import cn.tycoding.boot.modules.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:49:21
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    IPage<Article> articleList(IPage<Article> page, Long id);
}
