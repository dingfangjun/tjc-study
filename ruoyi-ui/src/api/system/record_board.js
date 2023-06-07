import request from '@/utils/request'

// 查询留言板列表
export function listRecord_board(query) {
  return request({
    url: '/system/record_board/list',
    method: 'get',
    params: query
  })
}

// 查询留言板详细
export function getRecord_board(id) {
  return request({
    url: '/system/record_board/' + id,
    method: 'get'
  })
}

// 新增留言板
export function addRecord_board(data) {
  return request({
    url: '/system/record_board',
    method: 'post',
    data: data
  })
}

// 修改留言板
export function updateRecord_board(data) {
  return request({
    url: '/system/record_board',
    method: 'put',
    data: data
  })
}

// 删除留言板
export function delRecord_board(id) {
  return request({
    url: '/system/record_board/' + id,
    method: 'delete'
  })
}
