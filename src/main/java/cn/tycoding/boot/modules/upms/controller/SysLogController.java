package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.upms.entity.SysLog;
import cn.tycoding.boot.modules.upms.service.SysLogService;
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
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/log")
@Api(value = "系统日志表接口", tags = "系统日志表接口")
public class SysLogController {

    private final SysLogService sysLogService;

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Dict> list(@RequestBody SysLog sysLog, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysLogService.list(sysLog, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysLog> findById(@PathVariable Long id) {
        return R.ok(sysLogService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody SysLog sysLog) {
        sysLogService.add(sysLog);
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
