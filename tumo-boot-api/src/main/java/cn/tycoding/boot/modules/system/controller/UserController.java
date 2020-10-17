package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.constant.AuthConstant;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.common.utils.SecurityUtil;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.system.entity.User;
import cn.tycoding.boot.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户表(User)表控制层
 *
 * @author tycoding
 * @since 2020-10-14 14:32:30
 */
@RestController
@AllArgsConstructor
@RequestMapping(AuthConstant.API_PATH + "/user")
@Api(value = "用户表接口", tags = "用户表接口")
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息")
    public R<UserInfo> info() {
        return new R<>(userService.info(SecurityUtil.getUsername()));
    }

    @GetMapping("/getMenus/{id}")
    @ApiOperation(value = "根据用户ID获取菜单")
    public R getMenuByUserId(@PathVariable("id") Long id) {
        return new R<>(userService.getMenuByUserId(id));
    }

    /**
     * 校验当前名称是否已存在
     *
     * @param user id:当前修改对象的ID
     *             name:需要校验的名称
     * @return Boolean
     */
    @PostMapping("/checkName")
    @ApiOperation(value = "校验名称是否已存在")
    public R<Boolean> checkName(@RequestBody User user) {
        return new R<>(userService.checkName(user));
    }

    @PostMapping("/filter/list")
    @ApiOperation(value = "条件查询")
    public R<List<User>> list(@RequestBody User user) {
        return new R<>(userService.list(user));
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页、条件查询")
    public R<Map<String, Object>> list(@RequestBody User user, QueryPage queryPage) {
        return new R<>(super.getData(userService.list(user, queryPage)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<User> findById(@PathVariable Long id) {
        return new R<>(userService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public R<User> add(@RequestBody UserInfo userInfo) {
        userService.add(userInfo);
        return new R<>();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public R update(@RequestBody UserInfo userInfo) {
        userService.update(userInfo);
        return new R();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除")
    public R delete(@PathVariable Long id) {
        userService.delete(id);
        return new R();
    }
}
