package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TActivityMapper;
import com.ruoyi.system.domain.TActivity;
import com.ruoyi.system.service.ITActivityService;

/**
 * 活动Service业务层处理
 * 
 * @author 丁方军
 * @date 2023-05-24
 */
@Service
public class TActivityServiceImpl implements ITActivityService 
{
    @Autowired
    private TActivityMapper tActivityMapper;

    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    @Override
    public TActivity selectTActivityById(Long id)
    {
        return tActivityMapper.selectTActivityById(id);
    }

    /**
     * 查询活动列表
     * 
     * @param tActivity 活动
     * @return 活动
     */
    @Override
    public List<TActivity> selectTActivityList(TActivity tActivity)
    {
        return tActivityMapper.selectTActivityList(tActivity);
    }

    /**
     * 新增活动
     * 
     * @param tActivity 活动
     * @return 结果
     */
    @Override
    public int insertTActivity(TActivity tActivity)
    {
        return tActivityMapper.insertTActivity(tActivity);
    }

    /**
     * 修改活动
     * 
     * @param tActivity 活动
     * @return 结果
     */
    @Override
    public int updateTActivity(TActivity tActivity)
    {
        return tActivityMapper.updateTActivity(tActivity);
    }

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteTActivityByIds(Long[] ids)
    {
        return tActivityMapper.deleteTActivityByIds(ids);
    }

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    @Override
    public int deleteTActivityById(Long id)
    {
        return tActivityMapper.deleteTActivityById(id);
    }
}
