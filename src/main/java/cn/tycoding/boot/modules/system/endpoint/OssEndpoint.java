package cn.tycoding.boot.modules.system.endpoint;

import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.modules.system.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单独定义文件上传端点接口
 *
 * @author tycoding
 * @since 2021/5/25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_BASE + "/oss")
public class OssEndpoint {

    private final OssService ossService;

    @PostMapping("/put")
    public R put(MultipartFile file) {
        return R.ok(ossService.put(file));
    }

}
