import request from '@/utils/request'

export function userList(query) {
  return request({
    url: `/system/user/list?page=${query.page}&limit=${query.limit}`,
    method: 'post'
  })
}
