import http from '@/utils/request'

/**
 * 获取文章标题列表接口
 */
export function titleList() {
	return http.request({
		url: '/article/title/list',
		method: 'get'
	})
}
