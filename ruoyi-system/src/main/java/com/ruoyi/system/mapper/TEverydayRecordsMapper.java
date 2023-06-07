package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TEverydayRecords;

/**
 * 每日茶经Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-24
 */
public interface TEverydayRecordsMapper 
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
     * 删除每日茶经
     * 
     * @param id 每日茶经主键
     * @return 结果
     */
    public int deleteTEverydayRecordsById(Long id);

    /**
     * 批量删除每日茶经
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTEverydayRecordsByIds(Long[] ids);
}
