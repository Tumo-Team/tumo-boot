package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.upms.dto.SysUserDTO;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表(User)表控制层
 *
 * @author tycoding
 * @since 2021/5/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/user")
@Api(value = "用户表接口", tags = "用户表接口")
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息")
    public R<UserInfo> info() {
        UserInfo userInfo = sysUserService.info(AuthUtil.getUsername());
        userInfo.getUser().setPassword(null);
        return R.ok(userInfo);
    }

    @GetMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(SysUser sysUser) {
        return R.ok(sysUserService.checkName(sysUser));
    }

    @GetMapping("/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysUser>> list(SysUser sysUser) {
        return R.ok(sysUserService.list(sysUser));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页、条件查询")
    public R<Dict> page(SysUserDTO user, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysUserService.page(user, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysUserDTO> findById(@PathVariable Long id) {
        return R.ok(sysUserService.findById(id));
    }

    @PostMapping
    @ApiLog("新增用户")
    @ApiOperation(value = "新增用户")
    @PreAuthorize("@auth.hasAuth('upms:user:add')")
    public R<SysUser> add(@RequestBody SysUserDTO user) {
        sysUserService.add(user);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改用户")
    @ApiOperation(value = "修改用户")
    @PreAuthorize("@auth.hasAuth('upms:user:update')")
    public R update(@RequestBody SysUserDTO user) {
        sysUserService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除用户")
    @ApiOperation(value = "删除用户")
    @PreAuthorize("@auth.hasAuth('upms:user:delete')")
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
}
