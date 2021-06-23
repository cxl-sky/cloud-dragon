import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auth-server/oauth/token',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user-server/users/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/auth-server/oauth/logout',
    method: 'post'
  })
}

export function fetchUserMenuList() {
  return request({
    url: '/user-server/users/permission/menus',
    method: 'get'
  })
}

export function userPage(data) {
  return request({
    url: '/user-server/users/page',
    method: 'get',
    params: data
  })
}
