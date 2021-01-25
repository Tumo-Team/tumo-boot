package cn.tycoding.boot.common.core.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一请求响应结果封装
 *
 * @author tycoding
 * @since 2020/10/9
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态码")
    private int code = HttpCode.SUCCESS.code;

    @ApiModelProperty("返回消息")
    private String msg = HttpCode.SUCCESS.msg;

    @ApiModelProperty("承载数据")
    private T data;

    public R() {
        super();
    }

    public static <T> R<T> data(T data) {
        return new R(data);
    }

    public static <T> R<T> data(T data, HttpCode httpCode) {
        return new R(data, httpCode);
    }

    public R(T data) {
        this.data = data;
    }

    public R(HttpCode httpCode) {
        this.code = httpCode.code;
        this.msg = httpCode.msg;
    }

    public R(T data, HttpCode httpCode) {
        this.data = data;
        this.code = httpCode.code;
        this.msg = httpCode.msg;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.code = HttpCode.INTERNAL_SERVER_ERROR.code;
        this.msg = e.getMessage();
    }
}
