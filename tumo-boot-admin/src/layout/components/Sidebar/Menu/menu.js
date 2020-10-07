import Menu from 'ant-design-vue/es/menu'
import Icon from 'ant-design-vue/es/icon'

import path from 'path'
import { isExternal } from '@/utils/validate'

const { Item, SubMenu } = Menu

export default {
  name: 'SMenu',
  props: {
    menu: {
      type: Array,
      required: true
    },
    theme: {
      type: String,
      required: false,
      default: 'dark'
    },
    mode: {
      type: String,
      required: false,
      default: 'inline'
    },
    collapsed: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    this.onlyOneChild = null
    return {
      openKeys: [],
      selectedKeys: [],
      cachedOpenKeys: []
    }
  },
  computed: {
    rootSubmenuKeys: (vm) => {
      const keys = []
      vm.menu.forEach((item) => keys.push(item.path))
      return keys
    }
  },
  mounted() {
    this.updateMenu()
  },
  watch: {
    collapsed(val) {
      if (val) {
        this.cachedOpenKeys = this.openKeys.concat()
        this.openKeys = []
      } else {
        this.openKeys = this.cachedOpenKeys
      }
    },
    $route: function() {
      this.updateMenu()
    }
  },
  methods: {
    // select menu item
    onOpenChange(openKeys) {
      // 在水平模式下时执行，并且不再执行后续
      if (this.mode === 'horizontal') {
        this.openKeys = openKeys
        return
      }
      // 非水平模式时
      const latestOpenKey = openKeys.find((key) => !this.openKeys.includes(key))
      if (!this.rootSubmenuKeys.includes(latestOpenKey)) {
        this.openKeys = openKeys
      } else {
        this.openKeys = latestOpenKey ? [latestOpenKey] : []
      }
    },
    updateMenu() {
      const routes = this.$route.matched.concat()
      const { hidden } = this.$route.meta
      if (routes.length >= 3 && hidden) {
        routes.pop()
        this.selectedKeys = [routes[routes.length - 1].path]
      } else {
        this.selectedKeys = [routes.pop().path]
      }
      const openKeys = []
      if (this.mode === 'inline') {
        routes.forEach((item) => {
          openKeys.push(item.path)
        })
      }

      this.collapsed ? (this.cachedOpenKeys = openKeys) : (this.openKeys = openKeys)
    },

    // 判断节点是否只有一层
    hasOneShowingChild(children = [], parent) {
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          // Temp set(will be used if only has one showing child)
          this.onlyOneChild = item
          return true
        }
      })
      // When there is only one child router, the child router is displayed by default
      if (showingChildren.length === 1) {
        return true
      }
      // Show parent if there are no child router to display
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ... parent, path: parent.path, noShowingChildren: true }
        return true
      }
      return false
    },
    // 封装path
    resolvePath(parentPath, routePath) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(parentPath)) {
        return parentPath
      }
      return path.resolve(parentPath, routePath)
    },

    // render
    renderItem(parentPath, menu) {
      // menu.path = this.resolvePath(parentPath, menu.path)
      // 判断Menu是否隐藏
      if (!menu.hidden) {
        // 判断Menu是否只有一层，或者children只有一层，都渲染为一层节点
        if (this.hasOneShowingChild(menu.children, menu) && (!this.onlyOneChild.children || this.onlyOneChild.noShowingChildren) && !menu.alwaysShow) {
          return this.renderMenuItem(parentPath, this.onlyOneChild)
        }
        // 包含子节点
        if (menu.children[0].children !== undefined) {
          // 三级以上节点

        }
        parentPath = this.resolvePath(parentPath, menu.path)
        return this.renderSubMenu(parentPath, menu)
      }
      return null
    },
    // 渲染单层节点
    renderMenuItem(parentPath, menu) {
      if (menu.meta) {
        let tag = ''
        let props = {}
        let attrs = {}
        const keyPath = this.resolvePath(parentPath, menu.path)
        // 判断是否是外链菜单
        // 注意：return中不能直接使用`<router-link>`或者`<a>`，会报错内存溢出
        if (isExternal(menu.path)) {
          tag = 'a'
          attrs = { href: menu.path, target: '_blank', rel: 'noopener' }
        } else {
          tag = 'router-link'
          props = { to: keyPath }
        }
        return (
          <Item {...{ key: keyPath }}>
            <tag {...{ props, attrs }}>
              {this.renderIcon(menu.meta.icon)}
              <span>{menu.meta.title}</span>
            </tag>
          </Item>
        )
      }
      return null
    },
    // 渲染包含子节点
    renderSubMenu(parentPath, menu) {
      const itemArr = []
      menu.children.forEach((item) => itemArr.push(this.renderItem(parentPath, item)))
      return (
        <SubMenu {...{ key: parentPath }}>
          <span slot='title'>
            {this.renderIcon(menu.meta.icon)}
            <span>{menu.meta.title}</span>
          </span>
          {itemArr}
        </SubMenu>
      )
    },
    renderIcon(icon) {
      if (icon === 'none' || icon === undefined) {
        return null
      }
      const props = {}
      typeof icon === 'object' ? (props.component = icon) : (props.type = icon)
      return <Icon {...{ props }} />
    }
  },

  render() {
    const { mode, theme, menu } = this
    const props = {
      mode: mode,
      theme: theme,
      openKeys: this.openKeys
    }
    const on = {
      select: (obj) => {
        this.selectedKeys = obj.selectedKeys
        this.$emit('select', obj)
      },
      openChange: this.onOpenChange
    }
    const menuTree = menu.map((item) => {
      if (item.hidden) {
        return null
      }
      return this.renderItem(item.path, item)
    })
    // {...{ props, on: on }}
    return (
      <Menu vModel={this.selectedKeys} {...{ props, on: on }}>
        {menuTree}
      </Menu>
    )
  }
}
