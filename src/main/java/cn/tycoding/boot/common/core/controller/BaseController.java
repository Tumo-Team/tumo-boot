package cn.tycoding.boot.common.core.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 公共层Controller方法提取
 *
 * @author tycoding
 * @since 2020/10/9
 */
public class BaseController {

    /**
     * 分页查询：格式化响应数据结构
     *
     * @param page 分页数据
     * @return 格式化后的Map对象
     */
    public Dict getData(IPage<?> page) {
        return Dict.create().set(CommonConstant.PAGE_ROWS, page.getRecords())
                .set(CommonConstant.PAGE_TOTAL, page.getTotal());
    }
}
