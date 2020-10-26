import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/blog'

/**
 * 分页、条件查询
 */
export function articleList(pagination, query) {
  return request({
    url: API_PREFIX + `/article/list?page=${pagination.page}&size=${pagination.size}`,
    method: 'post',
    query
  })
}

/**
 * 条件查询
 */
export function articleFilterList(data) {
  return request({
    url: API_PREFIX + `/article/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkArticleName(data) {
  return request({
    url: API_PREFIX + `/article/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByArticleId(id) {
  return request({
    url: API_PREFIX + `/article/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addArticle(data) {
  return request({
    url: API_PREFIX + `/article`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateArticle(data) {
  return request({
    url: API_PREFIX + `/article`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delArticle(id) {
  return request({
    url: API_PREFIX + `/article/${id}`,
    method: 'delete'
  })
}
