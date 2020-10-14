/** 本系统涉及的页面路由地址 **/

import Layout from '@/layout'

const tumoBootRouter = [
  {
    path: '/system',
    component: Layout,
    name: '系统模块',
    meta: {
      title: '系统模块',
      icon: 'alert'
    },
    children: [
      {
        path: 'user',
        name: '用户管理',
        component: () => import('@/views/modules/system/user/index'),
        meta: { title: '用户管理', icon: 'alert' }
      },
      {
        path: 'log',
        name: '日志模块',
        component: () => import('@/views/modules/system/user/index'),
        meta: { title: '日志模块', icon: 'alert' }
      }
    ]
  },
  {
    path: '/blog',
    component: Layout,
    name: '博客模块',
    meta: {
      title: '博客模块',
      icon: 'alert'
    },
    children: [
      {
        path: 'article',
        name: '文章管理',
        component: () => import('@/views/modules/blog/article/index'),
        meta: { title: '文章管理', icon: 'alert' }
      },
      {
        path: 'tag',
        name: '标签管理',
        component: () => import('@/views/modules/blog/tag/index'),
        meta: { title: '标签管理', icon: 'alert' }
      },
      {
        path: 'category',
        name: '分类管理',
        component: () => import('@/views/modules/blog/category/index'),
        meta: { title: '分类管理', icon: 'alert' }
      },
      {
        path: 'comment',
        name: '评论管理',
        component: () => import('@/views/modules/blog/comment/index'),
        meta: { title: '评论管理', icon: 'alert' }
      }
    ]
  }
]

export default tumoBootRouter
