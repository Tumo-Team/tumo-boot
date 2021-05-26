package cn.tycoding.boot.modules.oss.service;

import cn.tycoding.boot.modules.oss.entity.OssFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2021/5/25
 */
public interface OssService {

    OssFile put(MultipartFile file);

}
