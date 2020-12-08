package cn.tycoding.boot.modules.setting.controller;

import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.modules.setting.entity.Log;
import cn.tycoding.boot.modules.setting.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表(Log)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 16:53:52
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiConstant.API_SETTING_PREFIX + "/log")
@Api(value = "系统日志表接口", tags = "系统日志表接口")
public class LogController extends BaseController {

    private final LogService logService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Log>> list(@RequestBody Log log) {
        return new R<>(logService.list(log));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody Log log, QueryPage queryPage) {
        return new R<>(super.getData(logService.list(log, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Log> findById(@PathVariable Long id) {
        return new R<>(logService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Log log) {
        logService.add(log);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Log log) {
        logService.update(log);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        logService.delete(id);
        return new R();
    }
}
