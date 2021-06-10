package cn.tycoding.boot.modules.upms.service.impl;

import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.constant.CacheConstant;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.core.utils.MenuTreeUtil;
import cn.tycoding.boot.common.log.exception.ServiceException;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.SysMenu;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.mapper.SysMenuMapper;
import cn.tycoding.boot.modules.upms.service.SysMenuService;
import cn.tycoding.boot.modules.upms.service.SysRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public List<MenuTree<SysMenu>> tree(SysMenu sysMenu) {
        SysMenu menu = new SysMenu();
        menu.setName(sysMenu.getName());
        menu.setIsDisabled(sysMenu.getIsDisabled());
        return MenuTreeUtil.build(this.list(menu));
    }

    @Override
    @Cacheable(value = CacheConstant.MENU_DETAIL_KEY, key = "#userId")
    public List<MenuTree<SysMenu>> build(Long userId) {
        List<Long> roleIds = AuthUtil.getRoleIds();
        if (roleIds.size() == 0) {
            throw new TumoOAuth2Exception(AuthUtil.NOT_ROLE_ERROR);
        }
        if (AuthUtil.getRoleNames().contains(AuthUtil.ADMINISTRATOR)) {
            // 超级管理员，不做权限过滤
            roleIds.clear();
        }
        List<SysMenu> sysMenuList = baseMapper.build(roleIds, CommonConstant.MENU_TYPE_MENU);
        return MenuTreeUtil.build(sysMenuList);
    }

    @Override
    public List<SysMenu> getUserMenuList(List<SysRole> sysRoleList) {
        List<Long> roleIds = sysRoleList.stream().map(SysRole::getId).collect(Collectors.toList());
        return baseMapper.build(roleIds, null);
    }

    @Override
    public boolean checkName(SysMenu sysMenu) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getName, sysMenu.getName());
        if (sysMenu.getId() != null && sysMenu.getId() != 0) {
            queryWrapper.ne(SysMenu::getId, sysMenu.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    public List<SysMenu> list(SysMenu sysMenu) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysMenu>()
                .like(sysMenu.getName() != null, SysMenu::getName, sysMenu.getName())
                .eq(sysMenu.getIsDisabled() != null, SysMenu::getIsDisabled, sysMenu.getIsDisabled())
                .orderByAsc(SysMenu::getOrderNo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysMenu sysMenu) {
        this.format(sysMenu);
        baseMapper.insert(sysMenu);
    }

    private void format(SysMenu sysMenu) {
        if (CommonConstant.MENU_TYPE_MENU.equals(sysMenu.getType())) {
            if (sysMenu.getPath() == null) {
                throw new ServiceException("[path]参数不能为空");
            }
            if (sysMenu.getIcon() == null) {
                sysMenu.setIcon(CommonConstant.MENU_ICON);
            }
            if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
                // 父级节点
                if (sysMenu.getComponent() == null) {
                    sysMenu.setComponent("Layout");
                }
                if (!sysMenu.getPath().toLowerCase().startsWith("http") && !sysMenu.getPath().startsWith("/")) {
                    sysMenu.setPath("/" + sysMenu.getPath());
                }
            } else {
                // 子节点
                if (!sysMenu.getComponent().startsWith("/")) {
                    sysMenu.setComponent("/" + sysMenu.getComponent());
                }
                if (sysMenu.getPath().startsWith("/")) {
                    sysMenu.setPath(sysMenu.getPath().substring(1));
                }
            }
        }
        if (CommonConstant.MENU_TYPE_BUTTON.equals(sysMenu.getType())) {
            sysMenu.setPath(null);
            sysMenu.setIcon(null);
            sysMenu.setComponent(null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstant.MENU_DETAIL_KEY, allEntries = true)
    public void update(SysMenu sysMenu) {
        this.format(sysMenu);
        baseMapper.updateById(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstant.MENU_DETAIL_KEY, allEntries = true)
    public void delete(Long id) {
        List<SysMenu> list = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (list.size() > 0) {
            throw new ServiceException("该菜单包含子节点，不能删除");
        }
        sysRoleMenuService.deleteRoleMenusByMenuId(id);
        baseMapper.deleteById(id);
    }
}
