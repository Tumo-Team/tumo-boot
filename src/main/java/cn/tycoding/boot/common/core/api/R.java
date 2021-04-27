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

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> ok(T data) {
        return new R(data);
    }

    public static <T> R<T> ok(T data, HttpCode httpCode) {
        return new R(data, httpCode);
    }

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> ok(String msg) {
        return new R<>(HttpCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> ok(HttpCode httpCode) {
        return new R<>(httpCode);
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(HttpCode.FAILURE.getCode(), msg);
    }

    public static <T> R<T> fail(HttpCode httpCode) {
        return new R<>(httpCode);
    }

    public static <T> R<T> fail(Throwable e) {
        return new R<>(e);
    }

    protected R(T data) {
        this.data = data;
    }

    protected R(HttpCode httpCode) {
        this.code = httpCode.code;
        this.msg = httpCode.msg;
    }

    protected R(T data, HttpCode httpCode) {
        this.data = data;
        this.code = httpCode.code;
        this.msg = httpCode.msg;
    }

    protected R(Throwable e) {
        super();
        this.code = HttpCode.INTERNAL_SERVER_ERROR.code;
        this.msg = e.getMessage();
    }
}
