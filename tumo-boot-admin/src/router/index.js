import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import nestedRouter from './modules/nested'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'Dashboard', icon: 'home', affix: true }
      }
    ]
  },
  {
    path: 'external-link',
    children: [
      {
        path: 'https://github.com/Tumo-Team/T-AntV',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        hidden: true
      },
      {
        path: 'setting',
        component: () => import('@/views/profile/components/setting'),
        hidden: true
      },
      {
        path: 'setting/base',
        component: () => import('@/views/profile/components/base-set'),
        hidden: true
      },
      {
        path: 'setting/safe',
        component: () => import('@/views/profile/components/safe-set'),
        hidden: true
      }
    ]
  },
  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    meta: {
      title: 'Error Page',
      icon: 'frown'
    },
    children: [
      {
        path: '403',
        component: () => import('@/views/error/403'),
        name: '403',
        meta: { title: '403' }
      },
      {
        path: '404',
        component: () => import('@/views/error/404'),
        name: '404',
        meta: { title: '404' }
      },
      {
        path: '500',
        component: () => import('@/views/error/500'),
        name: '500',
        meta: { title: '500' }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

  nestedRouter,

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
