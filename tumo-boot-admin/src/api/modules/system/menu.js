import request from '@/utils/request'

// 构建左侧权限菜单
export function build() {
  return request({
    url: `/system/menu/build`,
    method: 'get'
  })
}
