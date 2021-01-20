package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.modules.upms.entity.Dept;
import cn.tycoding.boot.modules.upms.entity.User;
import cn.tycoding.boot.modules.upms.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/tree")
    @ApiOperation(value = "获取部门Tree")
    public R<List<Tree<Object>>> tree() {
        return new R(deptService.tree());
    }

    @GetMapping("/{id}/user/list")
    @ApiOperation(value = "获取所属用户列表")
    public R<List<User>> userList(@PathVariable Long id) {
        return new R(deptService.userList(id));
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param dept id:当前修改对象的ID
     *             name:需要校验的名称
     * @return true 当前名称可以用 false 当前名称已存在
     */
    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody Dept dept) {
        return new R<>(deptService.checkName(dept));
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
