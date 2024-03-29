package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.log.exception.ServiceException;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.system.entity.SysDictItem;
import cn.tycoding.boot.modules.system.service.SysDictItemService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典项表(SysDictItem)表控制层
 *
 * @author TyCoding
 * @since 2021-08-07 13:58:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/dict/item")
public class SysDictItemController {

    private final SysDictItemService sysDictItemService;

    @GetMapping("/list")
    public R<List<SysDictItem>> list(SysDictItem sysDictItem) {
        return R.ok(sysDictItemService.list(Wrappers.<SysDictItem>query().lambda()
                .eq(StringUtils.isNotEmpty(sysDictItem.getType()), SysDictItem::getType, sysDictItem.getType())));
    }

    @GetMapping("/page")
    public R list(@RequestParam Long dictId, QueryPage queryPage) {
        Page<SysDictItem> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(sysDictItemService.page(page, Wrappers.<SysDictItem>query().lambda()
                .eq(SysDictItem::getDictId, dictId)
                .orderByDesc(SysDictItem::getSort))));
    }

    @GetMapping("/{id}")
    public R<SysDictItem> findById(@PathVariable Long id) {
        return R.ok(sysDictItemService.getById(id));
    }

    @PostMapping
    @PreAuthorize("@auth.hasAuth('system:dict:item:add')")
    public R add(@RequestBody SysDictItem sysDictItem) {
        sysDictItemService.addDictItem(sysDictItem);
        return R.ok();
    }

    @PutMapping
    @PreAuthorize("@auth.hasAuth('system:dict:item:update')")
    public R update(@RequestBody SysDictItem sysDictItem) {
        sysDictItemService.updateById(sysDictItem);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@auth.hasAuth('system:dict:item:delete')")
    public R delete(@PathVariable Long id) {
        SysDictItem sysDictItem = sysDictItemService.getById(id);
        if (sysDictItem != null && sysDictItem.getIsSystem()) {
            throw new ServiceException("系统字典，不可删除");
        }
        sysDictItemService.removeById(id);
        return R.ok();
    }
}

