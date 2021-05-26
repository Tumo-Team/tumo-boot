package cn.tycoding.boot.modules.oss.endpoint;

import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.modules.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "文件上传端点", tags = "文件上传端点")
@RequestMapping("/oss")
public class OssEndpoint {

    private final OssService ossService;

    @PostMapping("/put")
    @ApiOperation(value = "文件上传")
    public R put(MultipartFile file) {
        return R.ok(ossService.put(file));
    }

}
