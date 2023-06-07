package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TogetherOutsideMapper;
import com.ruoyi.system.domain.TogetherOutside;
import com.ruoyi.system.service.ITogetherOutsideService;

/**
 * 活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
@Service
public class TogetherOutsideServiceImpl implements ITogetherOutsideService 
{
    @Autowired
    private TogetherOutsideMapper togetherOutsideMapper;

    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    @Override
    public TogetherOutside selectTogetherOutsideById(Integer id)
    {
        return togetherOutsideMapper.selectTogetherOutsideById(id);
    }

    /**
     * 查询活动列表
     * 
     * @param togetherOutside 活动
     * @return 活动
     */
    @Override
    public List<TogetherOutside> selectTogetherOutsideList(TogetherOutside togetherOutside)
    {
        return togetherOutsideMapper.selectTogetherOutsideList(togetherOutside);
    }

    /**
     * 新增活动
     * 
     * @param togetherOutside 活动
     * @return 结果
     */
    @Override
    public int insertTogetherOutside(TogetherOutside togetherOutside)
    {
        return togetherOutsideMapper.insertTogetherOutside(togetherOutside);
    }

    /**
     * 修改活动
     * 
     * @param togetherOutside 活动
     * @return 结果
     */
    @Override
    public int updateTogetherOutside(TogetherOutside togetherOutside)
    {
        return togetherOutsideMapper.updateTogetherOutside(togetherOutside);
    }

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteTogetherOutsideByIds(Integer[] ids)
    {
        return togetherOutsideMapper.deleteTogetherOutsideByIds(ids);
    }

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    @Override
    public int deleteTogetherOutsideById(Integer id)
    {
        return togetherOutsideMapper.deleteTogetherOutsideById(id);
    }
}
