import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/setting'

/**
 * 分页、条件查询
 */
export function loginLogList(pagination, data) {
  return request({
    url: API_PREFIX + `/loginLog/list?page=${pagination.page}&limit=${pagination.limit}`,
    method: 'post',
    data
  })
}

/**
 * 条件查询
 */
export function loginLogFilterList(data) {
  return request({
    url: API_PREFIX + `/loginLog/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkLoginLogName(data) {
  return request({
    url: API_PREFIX + `/loginLog/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByLoginLogId(id) {
  return request({
    url: API_PREFIX + `/loginLog/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addLoginLog(data) {
  return request({
    url: API_PREFIX + `/loginLog`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateLoginLog(data) {
  return request({
    url: API_PREFIX + `/loginLog`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delLoginLog(id) {
  return request({
    url: API_PREFIX + `/loginLog/${id}`,
    method: 'delete'
  })
}
