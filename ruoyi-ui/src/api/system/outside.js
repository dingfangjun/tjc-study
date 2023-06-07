import request from '@/utils/request'

// 查询活动列表
export function listOutside(query) {
  return request({
    url: '/system/outside/list',
    method: 'get',
    params: query
  })
}

// 查询活动详细
export function getOutside(id) {
  return request({
    url: '/system/outside/' + id,
    method: 'get'
  })
}

// 新增活动
export function addOutside(data) {
  return request({
    url: '/system/outside',
    method: 'post',
    data: data
  })
}

// 修改活动
export function updateOutside(data) {
  return request({
    url: '/system/outside',
    method: 'put',
    data: data
  })
}

// 删除活动
export function delOutside(id) {
  return request({
    url: '/system/outside/' + id,
    method: 'delete'
  })
}
