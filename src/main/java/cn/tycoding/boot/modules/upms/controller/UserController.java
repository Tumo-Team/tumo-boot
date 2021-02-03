package cn.tycoding.boot.modules.upms.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.utils.ExcelUtil;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.upms.dto.UserDTO;
import cn.tycoding.boot.modules.upms.entity.Role;
import cn.tycoding.boot.modules.upms.entity.User;
import cn.tycoding.boot.modules.upms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping(ApiConstant.API_UPMS_PREFIX + "/user")
@Api(value = "用户表接口", tags = "用户表接口")
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息")
    public R<UserInfo> info() {
        return R.data(userService.info(AuthUtil.getUsername()));
    }

    @GetMapping("/role/list/{id}")
    @ApiOperation(value = "根据用户ID查询角色ID集合")
    public R menuList(@PathVariable Long id) {
        List<Role> roleList = userService.roleList(id);
        return R.data(roleList.stream().map(Role::getId).collect(Collectors.toList()));
    }

    @PostMapping("/role/add/{id}")
    @ApiOperation(value = "分配角色")
    public R addRole(@RequestBody List<Long> roleList, @PathVariable Long id) {
        userService.addRole(roleList, id);
        return R.ok();
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody User user) {
        return R.data(userService.checkName(user));
    }

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<User>> list(@RequestBody User user) {
        return R.data(userService.list(user));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Dict> list(@RequestBody UserDTO user, QueryPage queryPage) {
        return R.data(super.getData(userService.list(user, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<UserDTO> findById(@PathVariable Long id) {
        return R.data(userService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R<User> add(@RequestBody UserDTO user) {
        userService.add(user);
        return R.ok();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody UserDTO user) {
        userService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        userService.delete(id);
        return R.ok();
    }

    @DeleteMapping("/resetPass")
    @ApiOperation(value = "重置密码")
    public R resetPass(@RequestBody User user) {
        userService.resetPass(user);
        return R.ok();
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出Excel")
    public void export(HttpServletResponse response) {
        List<User> list = userService.list();
        ExcelUtil.export(response, "用户数据", "用户数据", User.class, list);
    }
}
