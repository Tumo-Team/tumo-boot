package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.api.R;
import cn.tycoding.boot.common.controller.BaseController;
import cn.tycoding.boot.modules.system.entity.SysTag;
import cn.tycoding.boot.modules.system.service.SysTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 标签表(SysTag)表控制层
 *
 * @author tycoding
 * @since 2020-10-13 11:13:27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysTag")
public class SysTagController extends BaseController {

    private final SysTagService sysTagService;

    /**
     * 条件查询
     */
    @PostMapping("/filter/list")
    public R<List<SysTag>> list(@RequestBody SysTag sysTag) {
        return new R<>(sysTagService.list(sysTag));
    }

    /**
     * 分页、条件查询
     */
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody SysTag sysTag, QueryPage queryPage) {
        return new R<>(super.getData(sysTagService.list(sysTag, queryPage)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public R<SysTag> findById(@PathVariable Long id) {
        return new R<>(sysTagService.getById(id));
    }

    /**
     * 新增
     */
    @PostMapping
    public R add(@RequestBody SysTag sysTag) {
        sysTagService.add(sysTag);
        return new R();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@RequestBody SysTag sysTag) {
        sysTagService.update(sysTag);
        return new R();
    }

    /**
     * 根据ID删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        sysTagService.delete(id);
        return new R();
    }
}
