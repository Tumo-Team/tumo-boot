package cn.tycoding.boot.common.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一定义分页查询接口分页格式
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@AllArgsConstructor
public class QueryPage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页的记录数
     */
    private int limit;
}
