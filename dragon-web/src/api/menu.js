import request from '@/utils/request'

export function fetchUserMenuList() {
  return request({
    url: '/user-server/menus/permission',
    method: 'get'
  })
}

export function menuPage(data) {
  return request({
    url: '/user-server/menus/page',
    method: 'get',
    params: data
  })
}
