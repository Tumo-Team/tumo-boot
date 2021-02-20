package cn.tycoding.boot.modules.system.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.modules.system.entity.SysLog;
import cn.tycoding.boot.modules.system.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统日志表(Log)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 16:53:52
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/log")
@Api(value = "系统日志表接口", tags = "系统日志表接口")
public class SysLogController extends BaseController {

    private final SysLogService sysLogService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysLog>> list(@RequestBody SysLog sysLog) {
        return R.data(sysLogService.list(sysLog));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Dict> list(@RequestBody SysLog sysLog, QueryPage queryPage) {
        return R.data(super.getData(sysLogService.list(sysLog, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysLog> findById(@PathVariable Long id) {
        return R.data(sysLogService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody SysLog sysLog) {
        sysLogService.add(sysLog);
        return R.ok();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody SysLog sysLog) {
        sysLogService.update(sysLog);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        sysLogService.delete(id);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<SysLog> list = sysLogService.list();
        ExcelUtil.export(response, "日志数据", "日志数据", SysLog.class, list);
    }
}
