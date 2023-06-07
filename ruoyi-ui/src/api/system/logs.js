import request from '@/utils/request'

// 查询周六记录列表
export function listLogs(query) {
  return request({
    url: '/system/logs/list',
    method: 'get',
    params: query
  })
}
// 查询周六记录列表(根据月份)
export function listSaturdayLogsByMonth(query) {
  return request({
    url: '/system/logs/listByMonth',
    method: 'get',
    params: query
  })
}
// 查询周六记录详细
export function getLogs(id) {
  return request({
    url: '/system/logs/' + id,
    method: 'get'
  })
}

// 新增周六记录
export function addLogs(data) {
  return request({
    url: '/system/logs',
    method: 'post',
    data: data
  })
}

// 修改周六记录
export function updateLogs(data) {
  return request({
    url: '/system/logs',
    method: 'put',
    data: data
  })
}

// 删除周六记录
export function delLogs(id) {
  return request({
    url: '/system/logs/' + id,
    method: 'delete'
  })
}
