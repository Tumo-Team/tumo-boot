package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.SysUser;
import cn.tycoding.boot.modules.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户表(SysUser)表控制层
 *
 * @author tycoding
 * @since 2020-10-09 16:37:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    /**
     * 条件查询
     */
    @PostMapping("/filter/list")
    public R<List<SysUser>> list(@RequestBody SysUser sysUser) {
        return new R<>(sysUserService.list(sysUser));
    }

    /**
     * 分页、条件查询
     */
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody SysUser sysUser, QueryPage queryPage) {
        return new R<>(super.getData(sysUserService.list(sysUser, queryPage)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public R<SysUser> findById(@PathVariable Long id) {
        return new R<>(sysUserService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    public R save(@RequestBody SysUser sysUser) {
        sysUserService.add(sysUser);
        return new R();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return new R();
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysUserService.delete(id);
        return new R();
    }
}
