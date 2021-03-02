package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.upms.dto.SysUserDTO;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(User)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:32:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/user")
@Api(value = "用户表接口", tags = "用户表接口")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息")
    public R<UserInfo> info() {
        UserInfo userInfo = sysUserService.info(AuthUtil.getUsername());
        userInfo.getUser().setPassword(null);
        return R.data(userInfo);
    }

    @GetMapping("/role/list/{id}")
    @ApiOperation(value = "根据用户ID查询角色ID集合")
    public R menuList(@PathVariable Long id) {
        List<SysRole> sysRoleList = sysUserService.roleList(id);
        List<String> ids = sysRoleList.stream().map(SysRole::getId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList());
        return R.data(ids);
    }

    @PostMapping("/role/add/{id}")
    @ApiOperation(value = "分配角色")
    public R addRole(@RequestBody List<Long> roleList, @PathVariable Long id) {
        sysUserService.addRole(roleList, id);
        return R.ok();
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody SysUser sysUser) {
        return R.data(sysUserService.checkName(sysUser));
    }

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysUser>> list(@RequestBody SysUser sysUser) {
        return R.data(sysUserService.list(sysUser));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Dict> list(@RequestBody SysUserDTO user, QueryPage queryPage) {
        return R.data(super.getData(sysUserService.list(user, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysUserDTO> findById(@PathVariable Long id) {
        return R.data(sysUserService.findById(id));
    }

    @PostMapping
    @ApiLog("新增用户")
    @ApiOperation(value = "新增")
    public R<SysUser> add(@RequestBody SysUserDTO user) {
        sysUserService.add(user);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改用户")
    @ApiOperation(value = "修改")
    public R update(@RequestBody SysUserDTO user) {
        sysUserService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除用户")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        sysUserService.delete(id);
        return R.ok();
    }

    @DeleteMapping("/resetPass")
    @ApiLog("重置密码")
    @ApiOperation(value = "重置密码")
    public R resetPass(@RequestBody SysUser sysUser) {
        sysUserService.resetPass(sysUser);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<SysUser> list = sysUserService.list();
        ExcelUtil.export(response, "用户数据", "用户数据", SysUser.class, list);
    }
}
