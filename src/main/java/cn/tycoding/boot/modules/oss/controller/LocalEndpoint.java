package cn.tycoding.boot.modules.oss.controller;

import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.oss.entity.OssFile;
import cn.tycoding.boot.modules.oss.service.LocalOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资源文件表（OssFile）控制器
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@Api(value = "本地文件管理接口", tags = "本地文件管理接口")
@RequestMapping(ApiConstant.API_OSS_PREFIX + "/local")
public class LocalEndpoint {

    private final LocalOssService localOssService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public R list(OssFile ossFile, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(localOssService.page(ossFile, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R getById(@PathVariable Long id) {
        return R.ok(localOssService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "上传文件")
    public R add(@RequestParam MultipartFile file) {
        localOssService.add(file);
        return R.ok();
    }

    @PutMapping
    @ApiOperation(value = "修改文件信息")
    public R update(@RequestBody OssFile ossFile) {
        localOssService.update(ossFile);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文件")
    public R delete(@PathVariable Long id) {
        localOssService.delete(id);
        return R.ok();
    }
}
