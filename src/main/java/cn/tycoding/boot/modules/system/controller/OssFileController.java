package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.system.entity.OssFile;
import cn.tycoding.boot.modules.system.service.OssFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/oss")
public class OssFileController {

    private final OssFileService ossFileService;

    @GetMapping("/page")
    public R list(OssFile ossFile, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(ossFileService.page(ossFile, queryPage)));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        return R.ok(ossFileService.getById(id));
    }

    @PostMapping
    @PreAuthorize("@auth.hasAuth('system:oss:add')")
    public R add(@RequestBody OssFile ossFile) {
        ossFileService.save(ossFile);
        return R.ok();
    }

    @PostMapping("/put-list")
    @PreAuthorize("@auth.hasAuth('system:oss:add')")
    public R addList(@RequestBody List<OssFile> list) {
        ossFileService.saveBatch(list);
        return R.ok();
    }

    @PutMapping
    @PreAuthorize("@auth.hasAuth('system:oss:update')")
    public R update(@RequestBody OssFile ossFile) {
        ossFileService.updateById(ossFile);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('system:oss:delete')")
    public R delete(@PathVariable Long id) {
        ossFileService.delete(id);
        return R.ok();
    }
}
