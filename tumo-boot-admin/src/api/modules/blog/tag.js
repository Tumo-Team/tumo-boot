import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/blog'

/**
 * 分页、条件查询
 */
export function tagList(pagination, query) {
  return request({
    url: API_PREFIX + `/tag/list?page=${pagination.page}&size=${pagination.size}`,
    method: 'post',
    query
  })
}

/**
 * 条件查询
 */
export function tagFilterList(data) {
  return request({
    url: API_PREFIX + `/tag/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkTagName(data) {
  return request({
    url: API_PREFIX + `/tag/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByTagId(id) {
  return request({
    url: API_PREFIX + `/tag/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addTag(data) {
  return request({
    url: API_PREFIX + `/tag`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateTag(data) {
  return request({
    url: API_PREFIX + `/tag`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delTag(id) {
  return request({
    url: API_PREFIX + `/tag/${id}`,
    method: 'delete'
  })
}
