import Vue from 'vue'
import Router from 'uni-simple-router';

Vue.use(Router)

const router = new Router({
    routes: [
        {
            //注意：path必须跟pages.json中的地址对应，最前面别忘了加'/'哦
            path: '/pages/tabbar/index/index',
            aliasPath: '/',  //对于h5端你必须在首页加上aliasPath并设置为/
            name: 'index',
            meta: {
                title: '首页',
            }
        },
        {
            path: '/pages/tabbar/extend/index',
            name: 'extend',
            meta: {
                title: '教学',
            }
        },
        {
            path: '/pages/common/login/index',
            name: 'login',
            meta: {
                title: '登录',
            }
        }
    ]
});
export default router
