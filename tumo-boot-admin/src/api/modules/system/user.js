import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/system'

/**
 * 分页、条件查询
 */
export function userList(pageConf, query) {
  return request({
    url: API_PREFIX + `/user/list?page=${pageConf.page}&limit=${pageConf.limit}`,
    method: 'post',
    data: query
  })
}

/**
 * 条件查询（不分页）
 */
export function userFilterList(data) {
  return request({
    url: API_PREFIX + `/user/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkUserName(data) {
  return request({
    url: API_PREFIX + `/user/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByUserId(id) {
  return request({
    url: API_PREFIX + `/user/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addUser(data) {
  return request({
    url: API_PREFIX + `/user`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateUser(data) {
  return request({
    url: API_PREFIX + `/user`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delUser(id) {
  return request({
    url: API_PREFIX + `/user/${id}`,
    method: 'delete'
  })
}

/**
 * 重置密码
 */
export function resetPass(data) {
  return request({
    url: API_PREFIX + `/user/`,
    method: 'post',
    data
  })
}
