package cn.tycoding.boot.modules.oss.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.util.Date;

/**
 * 资源文件工具类
 *
 * @author tycoding
 * @since 2021/5/20
 */
public class OssUtil {
    /* 默认上传地址 */
    private static final String DEFAULT_UPLOAD_PATH = "static/upload";

    /**
     * 获取默认文件上传路径（磁盘绝对路径）
     */
    @SneakyThrows
    public static String getPath() {
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath() + DEFAULT_UPLOAD_PATH;
        return path + "/" + getBucket();
    }

    /**
     * 获取文件相对路径
     */
    public static String getRelativePath(String fileName) {
        return DEFAULT_UPLOAD_PATH + "/" + fileName;
    }

    /**
     * 获取文件绝对路径
     */
    public static String getAbsolutePath(String fileName) {
        return getPath() + "/" + fileName;
    }

    /**
     * 获取`yyyyMMddHHmmss`格式的时间戳
     */
    public static String getName() {
        return DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
    }

    /**
     * 根据原始文件格式拼接新文件名称
     */
    public static String getName(String name) {
        String suffix = FileUtil.getSuffix(name);
        return DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) + "." + suffix;
    }

    public static String getBucket() {
        return DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
    }
}
