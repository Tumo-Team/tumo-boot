package cn.tycoding.boot.common.mybatis.utils;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.mybatis.constant.MybatisConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Mybatis 工具类
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class MybatisUtil {

    /**
     * 分页查询：格式化响应数据结构
     *
     * @param page 分页数据
     * @return 格式化后的Map对象
     */
    public static Dict getData(IPage<?> page) {
        return Dict.create().set(MybatisConstant.PAGE_ROWS, page.getRecords())
                .set(MybatisConstant.PAGE_TOTAL, (int) page.getTotal());
    }

    /**
     * QueryPage对象转换为Page对象
     */
    public static <T> IPage<T> wrap(T t, QueryPage query) {
        return new Page<T>(query.getPage(), query.getLimit());
    }

}
