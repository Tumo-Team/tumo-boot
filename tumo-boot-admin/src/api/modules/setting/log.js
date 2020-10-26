import request from '@/utils/request'

/**
 * API 接口前缀
 */
export const API_PREFIX = '/setting'

/**
 * 分页、条件查询
 */
export function logList(pagination, query) {
  return request({
    url: API_PREFIX + `/log/list?page=${pagination.page}&size=${pagination.size}`,
    method: 'post',
    query
  })
}

/**
 * 条件查询
 */
export function logFilterList(data) {
  return request({
    url: API_PREFIX + `/log/filter/list`,
    method: 'post',
    data
  })
}

/**
 * 校验名称
 */
export function checkLogName(data) {
  return request({
    url: API_PREFIX + `/log/checkName`,
    method: 'post',
    data
  })
}

/**
 * 根据ID查询
 */
export function findByLogId(id) {
  return request({
    url: API_PREFIX + `/log/${id}`,
    method: 'get'
  })
}

/**
 * 新增
 */
export function addLog(data) {
  return request({
    url: API_PREFIX + `/log`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function updateLog(data) {
  return request({
    url: API_PREFIX + `/log`,
    method: 'put',
    data
  })
}

/**
 * 删除
 */
export function delLog(id) {
  return request({
    url: API_PREFIX + `/log/${id}`,
    method: 'delete'
  })
}
