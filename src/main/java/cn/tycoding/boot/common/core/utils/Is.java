package cn.tycoding.boot.common.core.utils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * @author tycoding
 * @since 2021/5/17
 */
public class Is {

    public static boolean isUrl(String url) {
        String reg = "(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\&%\\+\\$#_=]*)?";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(url).matches();
    }
}
