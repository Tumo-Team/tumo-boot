/** 本系统涉及的页面路由地址 **/

import Layout from '@/layout'

const tumoBootRouter = {
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
}

export default tumoBootRouter
