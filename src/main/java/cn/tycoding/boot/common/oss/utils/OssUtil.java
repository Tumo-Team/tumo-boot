package cn.tycoding.boot.common.oss.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.util.Date;

/**
 * Oss工具类
 *
 * @author tycoding
 * @since 2021/6/3
 */
public class OssUtil {

    /**
     * 获取Bucket文件夹名称
     *
     * @return 根据日期格式化的文件夹名称
     */
    public static String getBucket() {
        return "/" + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
    }

    /**
     * 根据随机值组建文件名称
     *
     * @param originFile 原始文件名称
     * @return 通过雪花算法获取新文件名称
     */
    public static String getName(String originFile) {
        String suffix = FileUtil.getSuffix(originFile);
        return IdWorker.getIdStr() + "." + suffix;
    }

    /**
     * 获取文件访问URL地址
     *
     * @param url      文件上传服务地址
     * @param bucket   Bucket文件夹路径
     * @param fileName 新文件名称
     * @return 拼接后的文件访问地址
     */
    public static String getUrl(String url, String bucket, String fileName) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return URLUtil.normalize(url + bucket + "/" + fileName);
    }
}
