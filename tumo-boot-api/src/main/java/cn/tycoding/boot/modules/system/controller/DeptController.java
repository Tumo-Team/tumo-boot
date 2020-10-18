package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.constant.ApiConstant;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.Dept;
import cn.tycoding.boot.modules.system.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 部门表(Dept)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:47:28
 */
@RestController
@AllArgsConstructor
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/dept")
@Api(value = "部门表接口", tags = "部门表接口")
public class DeptController extends BaseController {

    private final DeptService deptService;

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<Dept>> list(@RequestBody Dept dept) {
        return new R<>(deptService.list(dept));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody Dept dept, QueryPage queryPage) {
        return new R<>(super.getData(deptService.list(dept, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<Dept> findById(@PathVariable Long id) {
        return new R<>(deptService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R add(@RequestBody Dept dept) {
        deptService.add(dept);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody Dept dept) {
        deptService.update(dept);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        deptService.delete(id);
        return new R();
    }
}
