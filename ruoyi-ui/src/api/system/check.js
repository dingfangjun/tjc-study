import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function saveCheck(param) {
  return request({
    url: '/check-record/check',
    method: 'put',
    params: param
  })
}
