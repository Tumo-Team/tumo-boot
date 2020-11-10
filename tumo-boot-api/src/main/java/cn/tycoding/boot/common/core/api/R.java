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
    private int code = TumoHttpStatus.SUCCESS.code;

    @ApiModelProperty("返回消息")
    private String msg = TumoHttpStatus.SUCCESS.msg;

    @ApiModelProperty("承载数据")
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        this.data = data;
    }

    public R(TumoHttpStatus tumoHttpStatus) {
        this.code = tumoHttpStatus.code;
        this.msg = tumoHttpStatus.msg;
    }

    public R(T data, TumoHttpStatus tumoHttpStatus) {
        this.data = data;
        this.code = tumoHttpStatus.code;
        this.msg = tumoHttpStatus.msg;
    }

    public R(Throwable e) {
        super();
        this.code = TumoHttpStatus.INTERNAL_SERVER_ERROR.code;
        this.msg = e.getMessage();
    }
}
