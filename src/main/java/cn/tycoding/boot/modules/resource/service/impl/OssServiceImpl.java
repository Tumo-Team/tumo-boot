package cn.tycoding.boot.modules.resource.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.tycoding.boot.common.oss.props.LocalFileProperties;
import cn.tycoding.boot.common.oss.utils.OssUtil;
import cn.tycoding.boot.modules.resource.entity.OssFile;
import cn.tycoding.boot.modules.resource.service.OssService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * 文件上传服务类
 *
 * @author tycoding
 * @since 2021/5/25
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {

    private final LocalFileProperties properties;

    @Override
    public OssFile put(MultipartFile file) {
        return transfer(file);
    }

    /**
     * 写入文件
     */
    @SneakyThrows
    private OssFile transfer(MultipartFile file) {
        String bucket = OssUtil.getBucket();
        // 目标上传地址
        String targetPath = properties.getUploadPath() + bucket;
        // 创建文件夹
        if (!FileUtil.isDirectory(targetPath)) {
            FileUtil.mkdir(targetPath);
        }
        // 目标文件名
        String targetName = OssUtil.getName(file.getOriginalFilename());
        // 目标文件
        File targetFile = new File(targetPath, targetName);
        // 写入文件
        file.transferTo(targetFile);

        // 写入数据
        OssFile ossFile = new OssFile();
        ossFile.setOriginName(file.getOriginalFilename());
        ossFile.setTargetName(targetName);
        ossFile.setBucket(bucket);
        ossFile.setUrl(OssUtil.getUrl(properties.getRemotePath(), bucket, targetName));
        ossFile.setSize(file.getSize());
        ossFile.setType(FileUtil.getType(targetFile));
        ossFile.setDes(file.getOriginalFilename());
        ossFile.setCreateTime(new Date());
        return ossFile;
    }
}
