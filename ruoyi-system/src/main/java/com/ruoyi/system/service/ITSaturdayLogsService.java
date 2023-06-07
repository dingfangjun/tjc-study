package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TSaturdayLogs;

/**
 * 周六记录Service接口
 * 
 * @author ruoyi
 * @date 2022-04-20
 */
public interface ITSaturdayLogsService 
{
    /**
     * 查询周六记录
     * 
     * @param id 周六记录主键
     * @return 周六记录
     */
    public TSaturdayLogs selectTSaturdayLogsById(Long id);

    /**
     * 查询周六记录列表
     * 
     * @param tSaturdayLogs 周六记录
     * @return 周六记录集合
     */
    public List<TSaturdayLogs> selectTSaturdayLogsList(TSaturdayLogs tSaturdayLogs);
    /**
     * 查询周六记录列表
     *
     * @param tSaturdayLogs 周六记录
     * @return 周六记录集合
     */
    public List<TSaturdayLogs> selectTSaturdayLogsListByMonth(TSaturdayLogs tSaturdayLogs);

    /**
     * 新增周六记录
     * 
     * @param tSaturdayLogs 周六记录
     * @return 结果
     */
    public int insertTSaturdayLogs(TSaturdayLogs tSaturdayLogs);

    /**
     * 修改周六记录
     * 
     * @param tSaturdayLogs 周六记录
     * @return 结果
     */
    public int updateTSaturdayLogs(TSaturdayLogs tSaturdayLogs);

    /**
     * 批量删除周六记录
     * 
     * @param ids 需要删除的周六记录主键集合
     * @return 结果
     */
    public int deleteTSaturdayLogsByIds(Long[] ids);

    /**
     * 删除周六记录信息
     * 
     * @param id 周六记录主键
     * @return 结果
     */
    public int deleteTSaturdayLogsById(Long id);
}
