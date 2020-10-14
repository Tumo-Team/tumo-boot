package cn.tycoding.boot.modules.system.dao;

import cn.tycoding.boot.modules.system.entity.SysComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(SysComment)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-13 11:13:00
 */
@Mapper
public interface SysCommentDao extends BaseMapper<SysComment> {

}
