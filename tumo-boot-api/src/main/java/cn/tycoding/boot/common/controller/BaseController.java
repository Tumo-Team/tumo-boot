package cn.tycoding.boot.common.controller;

import cn.tycoding.boot.common.constant.CommonConstant;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> getData(IPage<?> page) {
        Map<String, Object> data = new HashMap<>();
        data.put(CommonConstant.PAGE_ROWS, page.getRecords());
        data.put(CommonConstant.PAGE_TOTAL, page.getTotal());
        return data;
    }
}
