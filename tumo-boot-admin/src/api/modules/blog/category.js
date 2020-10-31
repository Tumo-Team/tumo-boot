import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/blog'

/**
 * 分页、条件查询
 */
export function categoryList(pagination, data) {
  return request({
    url: API_PREFIX + `/category/list?page=${pagination.page}&limit=${pagination.limit}`,
    method: 'post',
    data
  })
}

/**
 * 条件查询
 */
export function categoryFilterList(data) {
  return request({
    url: API_PREFIX + `/category/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkCategoryName(data) {
  return request({
    url: API_PREFIX + `/category/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByCategoryId(id) {
  return request({
    url: API_PREFIX + `/category/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addCategory(data) {
  return request({
    url: API_PREFIX + `/category`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateCategory(data) {
  return request({
    url: API_PREFIX + `/category`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delCategory(id) {
  return request({
    url: API_PREFIX + `/category/${id}`,
    method: 'delete'
  })
}
