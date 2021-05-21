package cn.tycoding.boot.modules.oss.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.oss.entity.OssFile;
import cn.tycoding.boot.modules.oss.mapper.LocalOssMapper;
import cn.tycoding.boot.modules.oss.service.LocalOssService;
import cn.tycoding.boot.modules.oss.utils.OssUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @author tycoding
 * @since 2021/5/20
 */
@Service
public class LocalOssServiceImpl extends ServiceImpl<LocalOssMapper, OssFile> implements LocalOssService {

    @Override
    public IPage<OssFile> page(OssFile ossFile, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(ossFile, queryPage), new LambdaQueryWrapper<OssFile>()
                .like(StringUtils.isNotEmpty(ossFile.getOriginName()), OssFile::getOriginName, ossFile.getOriginName()));
    }

    @Override
    @SneakyThrows
    public void add(MultipartFile file) {
        String targetPath = OssUtil.getPath();
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

        // 写入数据库
        OssFile ossFile = new OssFile();
        ossFile.setOriginName(file.getOriginalFilename());
        ossFile.setTargetName(targetName);
        ossFile.setDes(file.getOriginalFilename());
        ossFile.setUrl(OssUtil.getRelativePath(targetName));
        ossFile.setSize(file.getSize());
        ossFile.setType(FileUtil.getType(targetFile));
        ossFile.setCreateTime(new Date());
        Console.log("localOss: {}", ossFile);
        baseMapper.insert(ossFile);
    }

    @Override
    public void update(OssFile ossFile) {
        ossFile.setUrl(null);
        baseMapper.updateById(ossFile);
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        OssFile ossFile = baseMapper.selectById(id);
        if (ossFile != null) {
            // 删除本地磁盘文件
            String path = OssUtil.getAbsolutePath(ossFile.getTargetName());
            FileUtil.del(new File(path));
            // 删除数据库
            baseMapper.deleteById(id);
        }
    }
}
