import request from '@/utils/request'

// 查询每日茶经列表
export function listRecords(query) {
  return request({
    url: '/system/records/list',
    method: 'get',
    params: query
  })
}

// 查询每日茶经列表
export function listRecordsLogsByMonth(query) {
  return request({
    url: '/system/records/listByMonth',
    method: 'get',
    params: query
  })
}
// 查询每日茶经详细
export function getRecords(id) {
  return request({
    url: '/system/records/' + id,
    method: 'get'
  })
}

// 新增每日茶经
export function addRecords(data) {
  return request({
    url: '/system/records',
    method: 'post',
    data: data
  })
}

// 修改每日茶经
export function updateRecords(data) {
  return request({
    url: '/system/records',
    method: 'put',
    data: data
  })
}

// 删除每日茶经
export function delRecords(id) {
  return request({
    url: '/system/records/' + id,
    method: 'delete'
  })
}
