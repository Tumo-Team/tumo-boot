import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/blog'

/**
 * 分页、条件查询
 */
export function commentList(pagination, data) {
  return request({
    url: API_PREFIX + `/comment/list?page=${pagination.page}&limit=${pagination.limit}`,
    method: 'post',
    data
  })
}

/**
 * 条件查询
 */
export function commentFilterList(data) {
  return request({
    url: API_PREFIX + `/comment/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkCommentName(data) {
  return request({
    url: API_PREFIX + `/comment/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByCommentId(id) {
  return request({
    url: API_PREFIX + `/comment/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addComment(data) {
  return request({
    url: API_PREFIX + `/comment`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateComment(data) {
  return request({
    url: API_PREFIX + `/comment`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delComment(id) {
  return request({
    url: API_PREFIX + `/comment/${id}`,
    method: 'delete'
  })
}
