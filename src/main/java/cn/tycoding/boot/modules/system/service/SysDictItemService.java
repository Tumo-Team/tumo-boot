package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.modules.system.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 字典项表(SysDictItem)表服务接口
 *
 * @author TyCoding
 * @since 2021-08-06
 */
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * 新增字典项
     *
     * @param sysDictItem 字典项信息
     */
    void addDictItem(SysDictItem sysDictItem);
}

