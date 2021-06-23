import request from '@/utils/request'

export function apiPage(data) {
  return request({
    url: '/user-server/apis/page',
    method: 'get',
    params: data
  })
}
