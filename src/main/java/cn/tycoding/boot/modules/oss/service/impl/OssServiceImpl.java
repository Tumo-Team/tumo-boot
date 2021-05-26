package cn.tycoding.boot.modules.oss.service.impl;

import cn.tycoding.boot.modules.oss.entity.OssFile;
import cn.tycoding.boot.modules.oss.service.OssService;
import cn.tycoding.boot.modules.oss.utils.OssUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2021/5/25
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {

    private final Environment environment;

    @Override
    public OssFile put(MultipartFile file) {
        return OssUtil.uploadLocal(file, environment);
    }

}
