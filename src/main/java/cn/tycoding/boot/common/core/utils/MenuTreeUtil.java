package cn.tycoding.boot.common.core.utils;

import cn.tycoding.boot.modules.upms.dto.MenuMeta;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.Menu;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * 一级节点结构：
      {
          path: '/',
          component: Layout,
          children: [
              {
                  path: '/test',
                  name: 'Test',
                  component: () => import('@/views/modules/test/index'),
                  meta: { title: 'Test', icon: 'radar-chart' }
              }
          ]
      }
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
        // 只有一级节点的节点
        List<MenuTree<Menu>> parentList = tree.stream().filter(i -> i.getChildren() == null || i.getChildren().size() == 0).collect(Collectors.toList());
        parentList.forEach(node -> {
            MenuTree<Menu> child = new MenuTree<>();
            BeanUtils.copyProperties(node, child);
            List<MenuTree<Menu>> childList = new ArrayList<>();
            childList.add(child);
            node = new MenuTree<>();
            node.setPath("/");
            node.setComponent("Layout");
            node.setChildren(childList);
        });
        return tree;
    }
}
