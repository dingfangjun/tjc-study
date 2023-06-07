package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TFriendSquare;

/**
 * 广场Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-28
 */
public interface TFriendSquareMapper 
{
    /**
     * 查询广场
     * 
     * @param id 广场主键
     * @return 广场
     */
    public TFriendSquare selectTFriendSquareById(Integer id);

    /**
     * 查询广场列表
     * 
     * @param tFriendSquare 广场
     * @return 广场集合
     */
    public List<TFriendSquare> selectTFriendSquareList(TFriendSquare tFriendSquare);

    /**
     * 新增广场
     * 
     * @param tFriendSquare 广场
     * @return 结果
     */
    public int insertTFriendSquare(TFriendSquare tFriendSquare);

    /**
     * 修改广场
     * 
     * @param tFriendSquare 广场
     * @return 结果
     */
    public int updateTFriendSquare(TFriendSquare tFriendSquare);

    /**
     * 删除广场
     * 
     * @param id 广场主键
     * @return 结果
     */
    public int deleteTFriendSquareById(Integer id);

    /**
     * 批量删除广场
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTFriendSquareByIds(Integer[] ids);
}
