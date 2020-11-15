import http from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/blog'

/**
 * 分页、条件查询
 */
export function articleList(pagination, data) {
  return http.request({
    url: API_PREFIX + `/article/list?page=${pagination.page}&limit=${pagination.limit}`,
    method: 'post',
    data
  })
}

/**
 * 条件查询
 */
export function articleFilterList(data) {
  return http.request({
    url: API_PREFIX + `/article/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByArticleId(id) {
  return http.request({
    url: API_PREFIX + `/article/${id}`,
    method: 'get'
  })
}
