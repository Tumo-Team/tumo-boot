package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.modules.system.entity.SysDict;
import cn.tycoding.boot.modules.system.entity.SysDictItem;
import cn.tycoding.boot.modules.system.mapper.SysDictItemMapper;
import cn.tycoding.boot.modules.system.mapper.SysDictMapper;
import cn.tycoding.boot.modules.system.service.SysDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典项表(SysDictItem)表服务实现类
 *
 * @author TyCoding
 * @since 2021-08-06
 */
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    private final SysDictMapper sysDictMapper;
    private final SysDictItemMapper sysDictItemMapper;

    @Override
    public void addDictItem(SysDictItem sysDictItem) {
        SysDict sysDict = sysDictMapper.selectById(sysDictItem.getDictId());
        if (sysDict != null) {
            sysDictItem.setDictId(sysDict.getId());
            sysDictItem.setIsSystem(sysDict.getIsSystem());

            sysDictItemMapper.insert(sysDictItem);
        }
    }
}

