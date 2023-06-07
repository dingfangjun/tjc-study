package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TFriendSquareMapper;
import com.ruoyi.system.domain.TFriendSquare;
import com.ruoyi.system.service.ITFriendSquareService;

/**
 * 广场Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-28
 */
@Service
public class TFriendSquareServiceImpl implements ITFriendSquareService 
{
    @Autowired
    private TFriendSquareMapper tFriendSquareMapper;

    /**
     * 查询广场
     * 
     * @param id 广场主键
     * @return 广场
     */
    @Override
    public TFriendSquare selectTFriendSquareById(Integer id)
    {
        return tFriendSquareMapper.selectTFriendSquareById(id);
    }

    /**
     * 查询广场列表
     * 
     * @param tFriendSquare 广场
     * @return 广场
     */
    @Override
    public List<TFriendSquare> selectTFriendSquareList(TFriendSquare tFriendSquare)
    {
        return tFriendSquareMapper.selectTFriendSquareList(tFriendSquare);
    }

    /**
     * 新增广场
     * 
     * @param tFriendSquare 广场
     * @return 结果
     */
    @Override
    public int insertTFriendSquare(TFriendSquare tFriendSquare)
    {
        return tFriendSquareMapper.insertTFriendSquare(tFriendSquare);
    }

    /**
     * 修改广场
     * 
     * @param tFriendSquare 广场
     * @return 结果
     */
    @Override
    public int updateTFriendSquare(TFriendSquare tFriendSquare)
    {
        return tFriendSquareMapper.updateTFriendSquare(tFriendSquare);
    }

    /**
     * 批量删除广场
     * 
     * @param ids 需要删除的广场主键
     * @return 结果
     */
    @Override
    public int deleteTFriendSquareByIds(Integer[] ids)
    {
        return tFriendSquareMapper.deleteTFriendSquareByIds(ids);
    }

    /**
     * 删除广场信息
     * 
     * @param id 广场主键
     * @return 结果
     */
    @Override
    public int deleteTFriendSquareById(Integer id)
    {
        return tFriendSquareMapper.deleteTFriendSquareById(id);
    }
}
