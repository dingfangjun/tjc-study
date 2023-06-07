package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.CheckRecord;
import com.ruoyi.system.domain.vo.request.ListCheckUserRequest;
import com.ruoyi.system.domain.vo.response.ListCheckUserResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 * @author sqwan
 * @date 2023-06-05
 */
public interface CheckRecordMapper extends BaseMapper<CheckRecord> {

    /**
     * @Author sqwan
     * @Description 查询考勤记录
     * @Date 14:48 2023/6/7
     * @Param [request]
     * @return java.util.List<com.ruoyi.system.domain.vo.response.ListCheckUserResponse>
     **/
    List<ListCheckUserResponse> listCheckUser(@Param("request") ListCheckUserRequest request);
}
