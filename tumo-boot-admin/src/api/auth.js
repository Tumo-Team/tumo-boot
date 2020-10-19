import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    headers: {
      'Authorization': 'Basic Y2xpZW50OnNlY3JldA=='
    },
    params: {
      username,
      password,
      grant_type: 'password'
    }
  })
}

export function getInfo() {
  return request({
    url: '/system/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/system/logout',
    method: 'post'
  })
}
