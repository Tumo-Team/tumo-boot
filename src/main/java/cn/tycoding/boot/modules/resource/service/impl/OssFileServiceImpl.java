package cn.tycoding.boot.modules.resource.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.common.oss.props.LocalFileProperties;
import cn.tycoding.boot.modules.resource.entity.OssFile;
import cn.tycoding.boot.modules.resource.mapper.OssFileMapper;
import cn.tycoding.boot.modules.resource.service.OssFileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 资源文件表（OssFile）服务实现类
 *
 * @author tycoding
 * @since 2021/5/20
 */
@Service
@RequiredArgsConstructor
public class OssFileServiceImpl extends ServiceImpl<OssFileMapper, OssFile> implements OssFileService {

    private final LocalFileProperties properties;

    @Override
    public IPage<OssFile> page(OssFile ossFile, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(ossFile, queryPage), new LambdaQueryWrapper<OssFile>()
                .like(StringUtils.isNotEmpty(ossFile.getOriginName()), OssFile::getOriginName, ossFile.getOriginName()));
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        OssFile ossFile = baseMapper.selectById(id);
        if (ossFile != null) {
            // 删除数据库
            baseMapper.deleteById(id);
            // 删除磁盘文件
            String path = properties.getUploadPath() + ossFile.getBucket() + "/" + ossFile.getOriginName();
            FileUtil.del(new File(path));
        }
    }
}
