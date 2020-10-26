import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/'

/**
 * 获取角色Tree
 */
export function menuTree() {
  return request({
    url: API_PREFIX + `/menu/tree`,
    method: 'get'
  })
}

/**
 * 条件查询
 */
export function menuFilterList(data) {
  return request({
    url: API_PREFIX + `/menu/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkMenuName(data) {
  return request({
    url: API_PREFIX + `/menu/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByMenuId(id) {
  return request({
    url: API_PREFIX + `/menu/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addMenu(data) {
  return request({
    url: API_PREFIX + `/menu`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateMenu(data) {
  return request({
    url: API_PREFIX + `/menu`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delMenu(id) {
  return request({
    url: API_PREFIX + `/menu/${id}`,
    method: 'delete'
  })
}
