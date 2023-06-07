package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TSongs;

/**
 * 赞美诗Service接口
 * 
 * @author ruoyi
 * @date 2023-05-27
 */
public interface ITSongsService 
{
    /**
     * 查询赞美诗
     * 
     * @param id 赞美诗主键
     * @return 赞美诗
     */
    public TSongs selectTSongsById(Long id);

    /**
     * 查询赞美诗列表
     * 
     * @param tSongs 赞美诗
     * @return 赞美诗集合
     */
    public List<TSongs> selectTSongsList(TSongs tSongs);

    /**
     * 新增赞美诗
     * 
     * @param tSongs 赞美诗
     * @return 结果
     */
    public int insertTSongs(TSongs tSongs);

    /**
     * 修改赞美诗
     * 
     * @param tSongs 赞美诗
     * @return 结果
     */
    public int updateTSongs(TSongs tSongs);

    /**
     * 批量删除赞美诗
     * 
     * @param ids 需要删除的赞美诗主键集合
     * @return 结果
     */
    public int deleteTSongsByIds(Long[] ids);

    /**
     * 删除赞美诗信息
     * 
     * @param id 赞美诗主键
     * @return 结果
     */
    public int deleteTSongsById(Long id);
}
