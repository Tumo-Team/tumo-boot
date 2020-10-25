import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/system'

/**
 * 条件查询
 */
export function roleFilterList(data) {
  return request({
    url: API_PREFIX + `/role/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 获取角色Tree
 */
export function roleTree() {
  return request({
    url: API_PREFIX + `/role/tree`,
    method: 'get'
  })
}

/**
 * 校验名称
 */
export function checkRoleName(data) {
  return request({
    url: API_PREFIX + `/role/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByRoleId(id) {
  return request({
    url: API_PREFIX + `/role/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addRole(data) {
  return request({
    url: API_PREFIX + `/role`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateRole(data) {
  return request({
    url: API_PREFIX + `/role`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delRole(id) {
  return request({
    url: API_PREFIX + `/role/${id}`,
    method: 'delete'
  })
}
