package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
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
@Api(value = "用户表接口", tags = "用户表接口" )
@RequestMapping("/user" )
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/filter/list" )
    @ApiOperation(value = "条件查询" )
    public R<List<User>> list(@RequestBody User user) {
        return new R<>(userService.list(user));
    }

    @PostMapping("/list" )
    @ApiOperation(value = "分页、条件查询" )
    public R<Map<String, Object>> list(@RequestBody User user, QueryPage queryPage) {
        return new R<>(super.getData(userService.list(user, queryPage)));
    }

    @GetMapping("/{id}" )
    @ApiOperation(value = "根据ID查询" )
    public R<User> findById(@PathVariable Long id) {
        return new R<>(userService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增" )
    public R add(@RequestBody User user) {
        userService.add(user);
        return new R();
    }

    @PutMapping
    @ApiOperation(value = "修改" )
    public R update(@RequestBody User user) {
        userService.update(user);
        return new R();
    }

    @DeleteMapping("/{id}" )
    @ApiOperation(value = "根据ID删除" )
    public R delete(@PathVariable Long id) {
        userService.delete(id);
        return new R();
    }
}
