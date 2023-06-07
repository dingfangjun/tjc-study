package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TSaturdayLogsMapper;
import com.ruoyi.system.domain.TSaturdayLogs;
import com.ruoyi.system.service.ITSaturdayLogsService;

/**
 * 周六记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-20
 */
@Service
public class TSaturdayLogsServiceImpl implements ITSaturdayLogsService 
{
    @Autowired
    private TSaturdayLogsMapper tSaturdayLogsMapper;

    /**
     * 查询周六记录
     * 
     * @param id 周六记录主键
     * @return 周六记录
     */
    @Override
    public TSaturdayLogs selectTSaturdayLogsById(Long id)
    {
        return tSaturdayLogsMapper.selectTSaturdayLogsById(id);
    }

    /**
     * 查询周六记录列表
     * 
     * @param tSaturdayLogs 周六记录
     * @return 周六记录
     */
    @Override
    public List<TSaturdayLogs> selectTSaturdayLogsList(TSaturdayLogs tSaturdayLogs)
    {
        return tSaturdayLogsMapper.selectTSaturdayLogsList(tSaturdayLogs);
    }
    /**
     * 查询周六记录列表
     *
     * @param tSaturdayLogs 周六记录(根据月份)
     * @return 周六记录
     */
    @Override
    public List<TSaturdayLogs> selectTSaturdayLogsListByMonth(TSaturdayLogs tSaturdayLogs)
    {
        return tSaturdayLogsMapper.selectTSaturdayLogsListByMonth(tSaturdayLogs);
    }
    /**
     * 新增周六记录
     * 
     * @param tSaturdayLogs 周六记录
     * @return 结果
     */
    @Override
    public int insertTSaturdayLogs(TSaturdayLogs tSaturdayLogs)
    {
        return tSaturdayLogsMapper.insertTSaturdayLogs(tSaturdayLogs);
    }

    /**
     * 修改周六记录
     * 
     * @param tSaturdayLogs 周六记录
     * @return 结果
     */
    @Override
    public int updateTSaturdayLogs(TSaturdayLogs tSaturdayLogs)
    {
        return tSaturdayLogsMapper.updateTSaturdayLogs(tSaturdayLogs);
    }

    /**
     * 批量删除周六记录
     * 
     * @param ids 需要删除的周六记录主键
     * @return 结果
     */
    @Override
    public int deleteTSaturdayLogsByIds(Long[] ids)
    {
        return tSaturdayLogsMapper.deleteTSaturdayLogsByIds(ids);
    }

    /**
     * 删除周六记录信息
     * 
     * @param id 周六记录主键
     * @return 结果
     */
    @Override
    public int deleteTSaturdayLogsById(Long id)
    {
        return tSaturdayLogsMapper.deleteTSaturdayLogsById(id);
    }
}
