package cn.tycoding.boot.modules.oss.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.URLUtil;
import cn.tycoding.boot.common.core.utils.IpUtil;
import cn.tycoding.boot.modules.oss.entity.OssFile;
import lombok.SneakyThrows;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    /* 默认URL访问地址 */
    private static final String DEFAULT_URL_PATH = "/upload";

    /**
     * 获取默认文件上传路径（磁盘绝对路径）
     */
    @SneakyThrows
    public static String getPath() {
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath() + DEFAULT_UPLOAD_PATH;
        return path + getBucket();
    }

    /**
     * 获取文件相对路径
     */
    public static String getRelativePath(String fileName) {
        return DEFAULT_URL_PATH + getBucket() + "/" + fileName;
    }

    /**
     * 获取文件URL
     */
    public static String getFileUrl(String fileName, Environment environment) {
        String ip = IpUtil.getAddress().getHostAddress();
        String port = environment.getProperty("local.server.port");
        return URLUtil.normalize(ip + ":" + port + getRelativePath(fileName));
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

    /**
     * 获取存储文件夹名称
     */
    public static String getBucket() {
        return "/" + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
    }

    /**
     * 本地服务器上传文件
     */
    @SneakyThrows
    public static OssFile uploadLocal(MultipartFile file, Environment environment) {
        String targetPath = getPath();
        // 创建文件夹
        if (!FileUtil.isDirectory(targetPath)) {
            FileUtil.mkdir(targetPath);
        }
        // 目标文件名
        String targetName = getName(file.getOriginalFilename());
        // 目标文件
        File targetFile = new File(targetPath, targetName);
        // 写入文件
        file.transferTo(targetFile);

        // 写入数据库
        OssFile ossFile = new OssFile();
        ossFile.setOriginName(file.getOriginalFilename());
        ossFile.setTargetName(targetName);
        ossFile.setDes(file.getOriginalFilename());
        ossFile.setUrl(getFileUrl(targetName, environment));
        ossFile.setSize(file.getSize());
        ossFile.setType(FileUtil.getType(targetFile));
        ossFile.setCreateTime(new Date());
        return ossFile;
    }

}
