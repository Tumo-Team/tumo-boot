package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.modules.upms.dto.SysRoleDTO;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色表(Role)表控制层
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/role")
@Api(value = "角色表接口", tags = "角色表接口")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping("/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysRole>> list(SysRole sysRole) {
        return R.ok(sysRoleService.list(new LambdaQueryWrapper<SysRole>().eq(SysRole::getStatus, true)));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取角色Tree")
    public R<List<Tree<Object>>> tree(SysRole sysRole) {
        return R.ok(sysRoleService.tree(sysRole));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysRoleDTO> findById(@PathVariable Long id) {
        return R.ok(sysRoleService.findById(id));
    }

    @PostMapping
    @ApiLog("新增角色")
    @ApiOperation(value = "新增角色")
    @PreAuthorize("@auth.hasAuth('upms:role:add')")
    public R add(@RequestBody SysRoleDTO sysRole) {
        sysRoleService.add(sysRole);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改角色")
    @ApiOperation(value = "修改角色")
    @PreAuthorize("@auth.hasAuth('upms:role:update')")
    public R update(@RequestBody SysRoleDTO sysRole) {
        sysRoleService.update(sysRole);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除角色")
    @ApiOperation(value = "删除角色")
    @PreAuthorize("@auth.hasAuth('upms:role:delete')")
    public R delete(@PathVariable Long id) {
        sysRoleService.delete(id);
        return R.ok();
    }
}
