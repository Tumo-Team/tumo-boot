package cn.tycoding.boot.modules.oss.controller;

import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.oss.entity.OssFile;
import cn.tycoding.boot.modules.oss.service.OssFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源文件表（OssFile）控制器
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@Api(value = "资源文件管理接口", tags = "资源文件管理接口")
@RequestMapping(ApiConstant.API_OSS_PREFIX)
public class OssFileController {

    private final OssFileService ossFileService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public R list(OssFile ossFile, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(ossFileService.page(ossFile, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R getById(@PathVariable Long id) {
        return R.ok(ossFileService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增文件信息")
    public R add(@RequestBody OssFile ossFile) {
        ossFileService.save(ossFile);
        return R.ok();
    }

    @PostMapping("/put-list")
    @ApiOperation(value = "新增文件信息（批量）")
    public R addList(@RequestBody List<OssFile> list) {
        ossFileService.saveBatch(list);
        return R.ok();
    }

    @PutMapping
    @ApiOperation(value = "修改文件信息")
    public R update(@RequestBody OssFile ossFile) {
        ossFileService.updateById(ossFile);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文件")
    public R delete(@PathVariable Long id) {
        ossFileService.delete(id);
        return R.ok();
    }
}
