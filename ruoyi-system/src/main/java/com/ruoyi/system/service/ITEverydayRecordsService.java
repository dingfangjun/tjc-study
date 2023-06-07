package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TEverydayRecords;

/**
 * 每日茶经Service接口
 * 
 * @author ruoyi
 * @date 2022-04-24
 */
public interface ITEverydayRecordsService 
{
    /**
     * 查询每日茶经
     * 
     * @param id 每日茶经主键
     * @return 每日茶经
     */
    public TEverydayRecords selectTEverydayRecordsById(Long id);

    /**
     * 查询每日茶经列表
     * 
     * @param tEverydayRecords 每日茶经
     * @return 每日茶经集合
     */
    public List<TEverydayRecords> selectTEverydayRecordsList(TEverydayRecords tEverydayRecords);

    /**
     * 查询每日茶经列表(根据月份)
     *
     * @param tEverydayRecords 每日茶经
     * @return 每日茶经集合
     */
    public List<TEverydayRecords> selectTEverydayRecordsListByMonth(TEverydayRecords tEverydayRecords);

    /**
     * 新增每日茶经
     * 
     * @param tEverydayRecords 每日茶经
     * @return 结果
     */
    public int insertTEverydayRecords(TEverydayRecords tEverydayRecords);

    /**
     * 修改每日茶经
     * 
     * @param tEverydayRecords 每日茶经
     * @return 结果
     */
    public int updateTEverydayRecords(TEverydayRecords tEverydayRecords);

    /**
     * 批量删除每日茶经
     * 
     * @param ids 需要删除的每日茶经主键集合
     * @return 结果
     */
    public int deleteTEverydayRecordsByIds(Long[] ids);

    /**
     * 删除每日茶经信息
     * 
     * @param id 每日茶经主键
     * @return 结果
     */
    public int deleteTEverydayRecordsById(Long id);
}
