package cn.tycoding.boot.common.core.api;

/**
 * 统一定义请求响应状态码
 *
 * @author tycoding
 * @since 2021/5/21
 */
@SuppressWarnings("all")
public enum HttpCode {
    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    UN_AUTHORIZED(401, "请求未授权"),
    AUTH_UN_AUTHORIZED(400, "用户名或密码错误"),
    CLIENT_UN_AUTHORIZED(401, "客户端请求未授权"),
    INVALID_GRANT(401, "用户名或密码错误"),
    FORBIDDEN(403, "没有访问权限"),
    NOT_FOUND(404, "404 没找到请求"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败");

    final int code;
    final String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    private HttpCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
