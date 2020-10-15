package cn.tycoding.boot.common.api;

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
    private int code = TumoBootHttpStatus.SUCCESS.code;

    @ApiModelProperty("返回消息")
    private String msg = TumoBootHttpStatus.SUCCESS.msg;

    @ApiModelProperty("承载数据")
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        this.data = data;
    }

    public R(TumoBootHttpStatus TumoBootHttpStatus) {
        this.code = TumoBootHttpStatus.code;
        this.msg = TumoBootHttpStatus.msg;
    }

    public R(T data, TumoBootHttpStatus TumoBootHttpStatus) {
        this.data = data;
        this.code = TumoBootHttpStatus.code;
        this.msg = TumoBootHttpStatus.msg;
    }

    public R(Throwable e) {
        super();
        this.code = TumoBootHttpStatus.INTERNAL_SERVER_ERROR.code;
        this.msg = e.getMessage();
    }
}
