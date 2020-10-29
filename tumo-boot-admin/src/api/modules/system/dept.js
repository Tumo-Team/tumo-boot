import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/system'

/**
 * 条件查询
 */
export function deptFilterList(data) {
  return request({
    url: API_PREFIX + `/dept/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 获取部门Tree
 */
export function deptTree() {
  return request({
    url: API_PREFIX + `/dept/tree`,
    method: 'get'
  })
}

/**
 * 获取所属用户列表
 */
export function deptUserList(id) {
  return request({
    url: API_PREFIX + `/dept/${id}/user/list`,
    method: 'get'
  })
}

/**
 * 校验名称
 */
export function checkDeptName(data) {
  return request({
    url: API_PREFIX + `/dept/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByDeptId(id) {
  return request({
    url: API_PREFIX + `/dept/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addDept(data) {
  return request({
    url: API_PREFIX + `/dept`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateDept(data) {
  return request({
    url: API_PREFIX + `/dept`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delDept(id) {
  return request({
    url: API_PREFIX + `/dept/${id}`,
    method: 'delete'
  })
}
