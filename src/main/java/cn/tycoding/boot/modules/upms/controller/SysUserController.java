package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.upms.dto.SysUserDTO;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.service.SysUserService;
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
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping("/info")
    public R<UserInfo> info() {
        UserInfo userInfo = sysUserService.info(AuthUtil.getUsername());
        userInfo.getUser().setPassword(null);
        return R.ok(userInfo);
    }

    @GetMapping("/checkName")
    public R<Boolean> checkName(SysUserDTO sysUser) {
        return R.ok(sysUserService.checkName(sysUser));
    }

    @GetMapping("/list")
    public R<List<SysUser>> list(SysUser sysUser) {
        return R.ok(sysUserService.list(sysUser));
    }

    @GetMapping("/page")
    public R<Dict> page(SysUserDTO user, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysUserService.page(user, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysUserDTO> findById(@PathVariable Long id) {
        return R.ok(sysUserService.findById(id));
    }

    @PostMapping
    @ApiLog("新增用户")
    @PreAuthorize("@auth.hasAuth('upms:user:add')")
    public R<SysUser> add(@RequestBody SysUserDTO user) {
        sysUserService.add(user);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改用户")
    @PreAuthorize("@auth.hasAuth('upms:user:update')")
    public R update(@RequestBody SysUserDTO user) {
        sysUserService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除用户")
    @PreAuthorize("@auth.hasAuth('upms:user:delete')")
    public R delete(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        if (user != null) {
            sysUserService.delete(id, user.getUsername());
        }
        return R.ok();
    }

    @GetMapping("/reset")
    @ApiLog("重置密码")
    @PreAuthorize("@auth.hasAuth('upms:user:reset')")
    public R reset(@RequestParam Long id, String password) {
        SysUser user = sysUserService.getById(id);
        if (user != null) {
            sysUserService.reset(id, password, user.getUsername());
        }
        return R.ok();
    }
}
