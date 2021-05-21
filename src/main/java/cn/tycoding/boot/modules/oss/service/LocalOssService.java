package cn.tycoding.boot.modules.oss.service;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.oss.entity.OssFile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资源文件表（OssFile）服务接口
 *
 * @author tycoding
 * @since 2021/5/20
 */
public interface LocalOssService extends IService<OssFile> {

    /**
     * 分页查询
     */
    IPage<OssFile> page(OssFile ossFile, QueryPage queryPage);

    /**
     * 上传文件
     */
    void add(MultipartFile file);

    /**
     * 修改文件信息
     */
    void update(OssFile ossFile);

    /**
     * 删除文件
     */
    void delete(Long id);
}
