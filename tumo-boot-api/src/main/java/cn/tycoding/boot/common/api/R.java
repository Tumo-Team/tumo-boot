package cn.tycoding.boot.common.api;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统一请求响应结果封装
 *
 * @author tycoding
 * @since 2020/10/9
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态码")
    private int code = RCode.SUCCESS.code;

    @ApiModelProperty("返回消息")
    private String msg = RCode.SUCCESS.message;

    @ApiModelProperty("承载数据")
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        this.data = data;
    }

    public R(RCode rCode) {
        this.code = rCode.code;
        this.msg = rCode.message;
    }

    public R(T data, RCode rCode) {
        this.data = data;
        this.code = rCode.code;
        this.msg = rCode.message;
    }

    public R(Throwable e) {
        super();
        this.code = RCode.INTERNAL_SERVER_ERROR.code;
        this.msg = e.getMessage();
    }
}
