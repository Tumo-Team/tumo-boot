package cn.tycoding.boot.modules.system.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.system.entity.SysLog;
import cn.tycoding.boot.modules.system.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 系统日志表(Log)表控制层
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/log")
public class SysLogController {

    private final SysLogService sysLogService;

    @GetMapping("/page")
    public R<Dict> list(SysLog sysLog, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysLogService.list(sysLog, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysLog> findById(@PathVariable Long id) {
        return R.ok(sysLogService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('system:log:delete')")
    public R delete(@PathVariable Long id) {
        sysLogService.delete(id);
        return R.ok();
    }
}
