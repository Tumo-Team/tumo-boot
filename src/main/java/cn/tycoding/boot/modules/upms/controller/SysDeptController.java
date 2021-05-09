package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.modules.upms.entity.SysDept;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:47:28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/dept")
@Api(value = "部门表接口", tags = "部门表接口")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysDept>> list(@RequestBody SysDept sysDept) {
        return R.ok(sysDeptService.list(sysDept));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取部门Tree")
    public R<List<Tree<Object>>> tree() {
        return R.ok(sysDeptService.tree());
    }

    @GetMapping("/{id}/user/list")
    @ApiOperation(value = "获取所属用户列表")
    public R<List<SysUser>> userList(@PathVariable Long id) {
        return R.ok(sysDeptService.userList(id));
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody SysDept sysDept) {
        return R.ok(sysDeptService.checkName(sysDept));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysDept> findById(@PathVariable Long id) {
        return R.ok(sysDeptService.getById(id));
    }

    @PostMapping
    @ApiLog("新增部门")
    @ApiOperation(value = "新增")
    public R add(@RequestBody SysDept sysDept) {
        sysDeptService.add(sysDept);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改部门")
    @ApiOperation(value = "修改")
    public R update(@RequestBody SysDept sysDept) {
        sysDeptService.update(sysDept);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除部门")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        sysDeptService.delete(id);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<SysDept> list = sysDeptService.list();
        ExcelUtil.export(response, "部门数据", "部门数据", SysDept.class, list);
    }
}
