import request from '@/utils/request'

// 分页、条件查询
export function userList(pageConf, query) {
  return request({
    url: `/system/user/list?page=${pageConf.page}&limit=${pageConf.limit}`,
    method: 'post',
    data: query
  })
}

// 条件查询（不分页）
export function userFilterList(data) {
  return request({
    url: `/system/user/filter/list`,
    method: 'post',
    data
  })
}

// 校验名称
export function checkUserName(data) {
  return request({
    url: `/system/user/checkName`,
    method: 'post',
    data
  })
}

// 根据ID查询
export function getUser(id) {
  return request({
    url: `/system/user/${id}`,
    method: 'get'
  })
}

// 新增
export function addUser(data) {
  return request({
    url: `/system/user`,
    method: 'post',
    data
  })
}

// 修改
export function updateUser(data) {
  return request({
    url: `/system/user`,
    method: 'put',
    data
  })
}

// 删除
export function delUser(id) {
  return request({
    url: `/system/user/${id}`,
    method: 'delete'
  })
}

// 重置密码
export function resetPass(data) {
  return request({
    url: `/system/user/`,
    method: 'post',
    data
  })
}
