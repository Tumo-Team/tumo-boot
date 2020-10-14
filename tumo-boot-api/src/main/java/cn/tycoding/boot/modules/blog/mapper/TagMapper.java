package cn.tycoding.boot.modules.blog.mapper;

import cn.tycoding.boot.modules.blog.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签表(Tag)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:50:50
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
