package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.modules.system.entity.SysDict;
import cn.tycoding.boot.modules.system.mapper.SysDictMapper;
import cn.tycoding.boot.modules.system.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典表(SysDict)表服务实现类
 *
 * @author TyCoding
 * @since 2021-08-06
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictMapper sysDictMapper;

}

