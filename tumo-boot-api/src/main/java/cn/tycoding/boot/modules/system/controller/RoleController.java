package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.Role;
import cn.tycoding.boot.modules.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色表(Role)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:25
 */
@RestController
@AllArgsConstructor
@Api(value = "角色表接口", tags = "角色表接口" )
@RequestMapping("/role" )
public class RoleController extends BaseController {

    private final RoleService roleService;

    @PostMapping("/filter/list" )
    @ApiOperation(value = "条件查询" )
    public R<List<Role>> list(@RequestBody Role role) {
        return new R<>(roleService.list(role));
    }

    @PostMapping("/list" )
    @ApiOperation(value = "分页、条件查询" )
    public R<Map<String, Object>> list(@RequestBody Role role, QueryPage queryPage) {
        return new R<>(super.getData(roleService.list(role, queryPage)));
    }

    @GetMapping("/{id}" )
    @ApiOperation(value = "根据ID查询" )
    public R<Role> findById(@PathVariable Long id) {
        return new R<>(roleService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增" )
    public R add(@RequestBody Role role) {
        roleService.add(role);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改" )
    public R update(@RequestBody Role role) {
        roleService.update(role);
        return new R();
    }

    @DeleteMapping("/{id}" )
    @ApiOperation(value = "根据ID删除" )
    public R delete(@PathVariable Long id) {
        roleService.delete(id);
        return new R();
    }
}
