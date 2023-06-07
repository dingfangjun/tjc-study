import request from '@/utils/request'

// 查询圣经列表
export function listBible(query) {
  return request({
    url: '/system/bible/list',
    method: 'get',
    params: query
  })
}

// 查询圣经详细
export function getBible(id) {
  return request({
    url: '/system/bible/' + id,
    method: 'get'
  })
}

// 新增圣经
export function addBible(data) {
  return request({
    url: '/system/bible',
    method: 'post',
    data: data
  })
}

// 修改圣经
export function updateBible(data) {
  return request({
    url: '/system/bible',
    method: 'put',
    data: data
  })
}

// 删除圣经
export function delBible(id) {
  return request({
    url: '/system/bible/' + id,
    method: 'delete'
  })
}
