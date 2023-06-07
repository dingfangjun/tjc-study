package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TogetherOutside;

/**
 * 活动Service接口
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
public interface ITogetherOutsideService 
{
    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    public TogetherOutside selectTogetherOutsideById(Integer id);

    /**
     * 查询活动列表
     * 
     * @param togetherOutside 活动
     * @return 活动集合
     */
    public List<TogetherOutside> selectTogetherOutsideList(TogetherOutside togetherOutside);

    /**
     * 新增活动
     * 
     * @param togetherOutside 活动
     * @return 结果
     */
    public int insertTogetherOutside(TogetherOutside togetherOutside);

    /**
     * 修改活动
     * 
     * @param togetherOutside 活动
     * @return 结果
     */
    public int updateTogetherOutside(TogetherOutside togetherOutside);

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键集合
     * @return 结果
     */
    public int deleteTogetherOutsideByIds(Integer[] ids);

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    public int deleteTogetherOutsideById(Integer id);
}
