package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.CheckRecord;
import com.ruoyi.system.domain.vo.request.ListCheckUserRequest;
import com.ruoyi.system.domain.vo.response.ListCheckUserResponse;

import java.util.List;

/**
 *  服务类
 * @author sqwan
 * @date 2023-06-05
 */
public interface CheckRecordService extends IService<CheckRecord> {

    /**
     * @Author sqwan
     * @Description 查看考勤记录
     * @Date 14:45 2023/6/7
     * @Param [request]
     * @return java.util.List<com.ruoyi.system.domain.vo.response.ListCheckUserResponse>
     **/
    List<ListCheckUserResponse> listCheckUser(ListCheckUserRequest request);
}
