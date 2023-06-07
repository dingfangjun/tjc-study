import request from '@/utils/request'

// 查询赞美诗列表
export function listSongs(query) {
  return request({
    url: '/system/songs/list',
    method: 'get',
    params: query
  })
}

// 查询赞美诗详细
export function getSongs(id) {
  return request({
    url: '/system/songs/' + id,
    method: 'get'
  })
}

// 新增赞美诗
export function addSongs(data) {
  return request({
    url: '/system/songs',
    method: 'post',
    data: data
  })
}

// 修改赞美诗
export function updateSongs(data) {
  return request({
    url: '/system/songs',
    method: 'put',
    data: data
  })
}

// 删除赞美诗
export function delSongs(id) {
  return request({
    url: '/system/songs/' + id,
    method: 'delete'
  })
}
