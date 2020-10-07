import router from './router'
import store from './store'
import {
	getToken
} from '@/utils/auth.js'


// 白名单
const whiteList = ['login']

/**
 * 路由守卫
 */
router.beforeEach(async (to, from, next) => {
	const hasToken = getToken()
	if (hasToken) {
		if (to.name === 'login') {
			next({
				name: 'login'
			})
		} else {
			try {
				await store.dispatch('user/getInfo')
				next()
			} catch (error) {
				await store.dispatch('user/resetToken')
				next({
					name: 'login'
				})
			}
		}
		next({
			name: 'login'
		})
	} else {
		/**
		 * 实现强制登录
		 */

		// has no token
		if (whiteList.indexOf(to.name) !== -1) {
			next()
		} else {
			// 如果无Token访问whiteList之外的页面都强制路由到login页面
			// next({
			// 	name: 'login',
			// 	NAVTYPE: 'push'
			// })
		}
	}
})
