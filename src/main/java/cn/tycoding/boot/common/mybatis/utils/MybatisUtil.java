package cn.tycoding.boot.common.mybatis.utils;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.mybatis.constant.MybatisConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author tycoding
 * @since 2021/5/9
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
                .set(MybatisConstant.PAGE_TOTAL, page.getTotal());
    }
}
