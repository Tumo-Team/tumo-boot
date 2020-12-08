package cn.tycoding.boot.modules.blog.mapper;

import cn.tycoding.boot.modules.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:48:06
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
