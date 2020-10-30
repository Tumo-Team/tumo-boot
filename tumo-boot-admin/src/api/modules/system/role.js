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
 * 获取菜单表基础Tree数据
 */
export function roleBaseTree() {
  return request({
    url: API_PREFIX + `/role/base/tree`,
    method: 'get'
  })
}

/**
 * 获取指定角色ID的所有菜单权限
 */
export function rolePermissionList(id) {
  return request({
    url: API_PREFIX + `/role/permission/list/${id}`,
    method: 'get'
  })
}

/**
 * 为指定角色分配菜单权限
 */
export function roleAddPermission(data, id) {
  return request({
    url: API_PREFIX + `/role/permission/add/${id}`,
    method: 'post',
    data
  })
}

/**
 * 获取所属用户列表
 */
export function roleUserList(id) {
  return request({
    url: API_PREFIX + `/role/${id}/user/list`,
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
