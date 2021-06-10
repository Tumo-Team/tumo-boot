package cn.tycoding.boot.common.core.utils;

import cn.tycoding.boot.modules.upms.dto.MenuMeta;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义封装Menu Tree
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class MenuTreeUtil {

    private static List<MenuTree<SysMenu>> init(List<SysMenu> list) {
        List<MenuTree<SysMenu>> treeList = new ArrayList<>();
        list.forEach(menu -> {
            MenuTree<SysMenu> tree = new MenuTree<>();
            tree.setId(menu.getId());
            tree.setParentId(menu.getParentId());
            tree.setName(menu.getName());
            tree.setPath(menu.getPath());
            tree.setType(menu.getType());
            tree.setComponent(menu.getComponent());
            tree.setPerms(menu.getPerms());
            tree.setMeta(new MenuMeta(menu.getName(), menu.getIcon()));
            tree.setOrderNo(menu.getOrderNo());
            tree.setDisabled(menu.getIsDisabled());
            tree.setIsExt(menu.getIsExt());
            tree.setKeepalive(menu.getIsKeepalive());
            tree.setShow(menu.getIsShow());
            treeList.add(tree);
        });
        return treeList;
    }


    public static List<MenuTree<SysMenu>> build(List<SysMenu> list) {
        List<MenuTree<SysMenu>> nodes = init(list);
        List<MenuTree<SysMenu>> tree = new ArrayList<>();
        nodes.forEach(node -> {
            Long pid = node.getParentId();
            if (pid == null || pid.equals(0L)) {
                // 父级节点
                if (node.getIsExt()) {
                    node.setComponent("IFrame");
                }
                tree.add(node);
                return;
            }
            for (MenuTree<SysMenu> c : nodes) {
                Long id = c.getId();
                if (id != null && id.equals(pid)) {
                    c.getChildren().add(node);
                    return;
                }
            }
        });
        return tree;
    }
}
