package cn.tycoding.boot.common.utils;

import cn.tycoding.boot.modules.system.dto.MenuMeta;
import cn.tycoding.boot.modules.system.dto.MenuTree;
import cn.tycoding.boot.modules.system.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2020/10/15
 */
public class MenuTreeUtil {

    public static List<MenuTree<Menu>> build(List<Menu> menus) {
        List<MenuTree<Menu>> treeList = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<Menu> tree = new MenuTree<>();
            tree.setId(menu.getId());
            tree.setName(menu.getName());
            tree.setPath(menu.getPath());
            tree.setMeta(new MenuMeta(menu.getName(), menu.getIcon()));
            tree.setComponent(menu.getComponent());
            tree.setPerms(menu.getPerms());
            tree.setHidden(menu.getHidden());
            tree.setFrame(menu.getFrame());
            tree.setParentId(menu.getParentId());
            treeList.add(tree);
        });
        return buildTree(treeList);
    }

    /**
     * 构建Tree树
     *
     * @param nodes 节点集合
     * @return
     */
    private static List<MenuTree<Menu>> buildTree(List<MenuTree<Menu>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<MenuTree<Menu>> tree = new ArrayList<>();
        nodes.forEach(node -> {
            Long pid = node.getParentId();
            if (pid == null || pid.equals(0L)) {
                node.setComponent("Layout");
                tree.add(node);
                return;
            }
            for (MenuTree<Menu> c : nodes) {
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
