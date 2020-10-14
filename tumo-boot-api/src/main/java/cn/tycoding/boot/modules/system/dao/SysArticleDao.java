package cn.tycoding.boot.modules.system.dao;

import cn.tycoding.boot.modules.system.entity.SysArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章表(SysArticle)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-13 11:11:26
 */
@Mapper
public interface SysArticleDao extends BaseMapper<SysArticle> {

}
