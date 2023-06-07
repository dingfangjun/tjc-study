package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TEverydayRecordsMapper;
import com.ruoyi.system.domain.TEverydayRecords;
import com.ruoyi.system.service.ITEverydayRecordsService;

/**
 * 每日茶经Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-24
 */
@Service
public class TEverydayRecordsServiceImpl implements ITEverydayRecordsService 
{
    @Autowired
    private TEverydayRecordsMapper tEverydayRecordsMapper;

    /**
     * 查询每日茶经
     * 
     * @param id 每日茶经主键
     * @return 每日茶经
     */
    @Override
    public TEverydayRecords selectTEverydayRecordsById(Long id)
    {
        return tEverydayRecordsMapper.selectTEverydayRecordsById(id);
    }

    /**
     * 查询每日茶经列表
     * 
     * @param tEverydayRecords 每日茶经
     * @return 每日茶经
     */
    @Override
    public List<TEverydayRecords> selectTEverydayRecordsList(TEverydayRecords tEverydayRecords)
    {
        return tEverydayRecordsMapper.selectTEverydayRecordsList(tEverydayRecords);
    }
    /**
     * 查询每日茶经列表(根据月份)
     *
     * @param tEverydayRecords 每日茶经
     * @return 每日茶经
     */
    @Override
    public List<TEverydayRecords> selectTEverydayRecordsListByMonth(TEverydayRecords tEverydayRecords)
    {
        return tEverydayRecordsMapper.selectTEverydayRecordsListByMonth(tEverydayRecords);
    }
    /**
     * 新增每日茶经
     * 
     * @param tEverydayRecords 每日茶经
     * @return 结果
     */
    @Override
    public int insertTEverydayRecords(TEverydayRecords tEverydayRecords)
    {
        return tEverydayRecordsMapper.insertTEverydayRecords(tEverydayRecords);
    }

    /**
     * 修改每日茶经
     * 
     * @param tEverydayRecords 每日茶经
     * @return 结果
     */
    @Override
    public int updateTEverydayRecords(TEverydayRecords tEverydayRecords)
    {
        return tEverydayRecordsMapper.updateTEverydayRecords(tEverydayRecords);
    }

    /**
     * 批量删除每日茶经
     * 
     * @param ids 需要删除的每日茶经主键
     * @return 结果
     */
    @Override
    public int deleteTEverydayRecordsByIds(Long[] ids)
    {
        return tEverydayRecordsMapper.deleteTEverydayRecordsByIds(ids);
    }

    /**
     * 删除每日茶经信息
     * 
     * @param id 每日茶经主键
     * @return 结果
     */
    @Override
    public int deleteTEverydayRecordsById(Long id)
    {
        return tEverydayRecordsMapper.deleteTEverydayRecordsById(id);
    }
}
