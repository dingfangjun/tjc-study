package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.CheckRecord;
import com.ruoyi.system.domain.vo.request.ListCheckUserRequest;
import com.ruoyi.system.domain.vo.response.ListCheckUserResponse;
import com.ruoyi.system.mapper.CheckRecordMapper;
import com.ruoyi.system.service.CheckRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 * @author sqwan
 * @date 2023-06-05
 */
@Service
public class CheckRecordServiceImpl extends ServiceImpl<CheckRecordMapper, CheckRecord> implements CheckRecordService {

    @Resource
    private CheckRecordMapper checkRecordMapper;

    @Override
    public List<ListCheckUserResponse> listCheckUser(ListCheckUserRequest request) {
        return checkRecordMapper.listCheckUser(request);
    }
}
