package cn.tycoding.boot.modules.system.mapper;

import cn.tycoding.boot.modules.system.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表(Dept)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:47:30
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

}
