package cn.tycoding.boot.common.core.utils;

import cn.tycoding.boot.modules.upms.dto.MenuMeta;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2020/10/15
 */
public class MenuTreeUtil {

    private static List<MenuTree<Menu>> init(List<Menu> list) {
        List<MenuTree<Menu>> treeList = new ArrayList<>();
        list.forEach(menu -> {
            MenuTree<Menu> tree = new MenuTree<>();
            tree.setId(menu.getId());
            tree.setName(menu.getName());
            tree.setPath(menu.getPath());
            tree.setType(menu.getType());
            tree.setMeta(new MenuMeta(menu.getName(), menu.getIcon()));
            tree.setComponent(menu.getComponent());
            tree.setPerms(menu.getPerms());
            tree.setHidden(menu.getHidden());
            tree.setFrame(menu.getFrame());
            tree.setParentId(menu.getParentId());
            treeList.add(tree);
        });
        return treeList;
    }


    public static List<MenuTree<Menu>> build(List<Menu> list) {
        List<MenuTree<Menu>> nodes = init(list);
        List<MenuTree<Menu>> tree = new ArrayList<>();
        nodes.forEach(node -> {
            Long pid = node.getParentId();
            if (pid == null || pid.equals(0L)) {
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

    /**
     * 一级节点结构：
     * {
     * path: '/',
     * component: Layout,
     * children: [
     * {
     * path: '/test',
     * name: 'Test',
     * component: () => import('@/views/modules/test/index'),
     * meta: { title: 'Test', icon: 'radar-chart' }
     * }
     * ]
     * }
     */
    public static List<MenuTree<Menu>> buildTree(List<Menu> list) {
        List<MenuTree<Menu>> nodes = new ArrayList<>();
        build(list).forEach(node -> {
            MenuTree<Menu> child = BeanUtil.copy(node, new MenuTree<>());
            if (node.getChildren().size() == 0) {
                // 只有一级节点
                List<MenuTree<Menu>> childList = new ArrayList<>();
                childList.add(child);
                nodes.add(new MenuTree<Menu>().setPath("/").setComponent("Layout")
                        .setRedirect(child.getPath()).setChildren(childList).setHidden(child.getHidden()));
            } else {
                // 包含子节点
                nodes.add(new MenuTree<Menu>().setPath(child.getPath()).setComponent("Layout")
                        .setRedirect(child.getPath()).setChildren(child.getChildren()).setMeta(child.getMeta()).setHidden(child.getHidden()));
            }
        });
        return nodes;
    }
}
