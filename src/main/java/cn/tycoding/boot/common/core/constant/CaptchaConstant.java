package cn.tycoding.boot.common.core.constant;

/**
 * 图片验证码常量
 *
 * @author tycoding
 * @since 2021/5/21
 */
public interface CaptchaConstant {

    /**
     * 验证码图片高度
     */
    int CAPTCHA_HEIGHT = 32;

    /**
     * 验证码图片宽度
     */
    int CAPTCHA_WIDTH = 110;

    /**
     * 验证码图片字符数量
     */
    int CAPTCHA_COUNT = 4;

    /**
     * 验证码图片干扰线数量
     */
    int CAPTCHA_CIRCLE_COUNT = 10;

    /**
     * 验证码过期时间（分钟）
     */
    int CAPTCHA_TIMEOUT = 10;
}
