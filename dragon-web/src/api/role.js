import request from '@/utils/request'

export function rolePage(data) {
  return request({
    url: '/user-server/roles/page',
    method: 'get',
    params: data
  })
}
