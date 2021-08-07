package cn.tycoding.boot.modules.system.controller;

import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.ApiConstant;
import cn.tycoding.boot.common.log.exception.ServiceException;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.system.entity.SysDict;
import cn.tycoding.boot.modules.system.service.SysDictService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典表(SysDict)表控制层
 *
 * @author TyCoding
 * @since 2021-08-06
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstant.API_SYSTEM_PREFIX + "/dict")
@Api(value = "字典表接口", tags = "字典表接口")
public class SysDictController {

    private final SysDictService sysDictService;

    @GetMapping("/list")
    @ApiOperation(value = "条件查询")
    public R<List<SysDict>> list(SysDict sysDict) {
        return R.ok(sysDictService.list());
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页、条件查询")
    public R list(SysDict sysDict, QueryPage queryPage) {
        Page<SysDict> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(sysDictService.page(page, Wrappers.<SysDict>query().lambda()
                .like(StringUtils.isNotEmpty(sysDict.getName()), SysDict::getName, sysDict.getName())
                .orderByDesc(SysDict::getSort))));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询")
    public R<SysDict> findById(@PathVariable Long id) {
        return R.ok(sysDictService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    @PreAuthorize("@auth.hasAuth('system:dict:add')")
    public R add(@RequestBody SysDict sysDict) {
        sysDictService.save(sysDict);
        return R.ok();
    }

    @PutMapping
    @ApiOperation(value = "修改")
    @PreAuthorize("@auth.hasAuth('system:dict:update')")
    public R update(@RequestBody SysDict sysDict) {
        sysDictService.updateById(sysDict);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("@auth.hasAuth('system:dict:delete')")
    public R delete(@PathVariable Long id) {
        SysDict sysDict = sysDictService.getById(id);
        if (sysDict != null && sysDict.getIsSystem()) {
            throw new ServiceException("系统字典，不可删除");
        }
        sysDictService.removeById(id);
        return R.ok();
    }

}

