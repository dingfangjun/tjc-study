import request from '@/utils/request'

// 查询广场列表
export function listSquare(query) {
  return request({
    url: '/system/square/list',
    method: 'get',
    params: query
  })
}

// 查询广场详细
export function getSquare(id) {
  return request({
    url: '/system/square/' + id,
    method: 'get'
  })
}

// 新增广场
export function addSquare(data) {
  return request({
    url: '/system/square',
    method: 'post',
    data: data
  })
}

// 修改广场
export function updateSquare(data) {
  return request({
    url: '/system/square',
    method: 'put',
    data: data
  })
}

// 删除广场
export function delSquare(id) {
  return request({
    url: '/system/square/' + id,
    method: 'delete'
  })
}
