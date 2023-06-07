package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TSongsMapper;
import com.ruoyi.system.domain.TSongs;
import com.ruoyi.system.service.ITSongsService;

/**
 * 赞美诗Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-27
 */
@Service
public class TSongsServiceImpl implements ITSongsService 
{
    @Autowired
    private TSongsMapper tSongsMapper;

    /**
     * 查询赞美诗
     * 
     * @param id 赞美诗主键
     * @return 赞美诗
     */
    @Override
    public TSongs selectTSongsById(Long id)
    {
        return tSongsMapper.selectTSongsById(id);
    }

    /**
     * 查询赞美诗列表
     * 
     * @param tSongs 赞美诗
     * @return 赞美诗
     */
    @Override
    public List<TSongs> selectTSongsList(TSongs tSongs)
    {
        return tSongsMapper.selectTSongsList(tSongs);
    }

    /**
     * 新增赞美诗
     * 
     * @param tSongs 赞美诗
     * @return 结果
     */
    @Override
    public int insertTSongs(TSongs tSongs)
    {
        return tSongsMapper.insertTSongs(tSongs);
    }

    /**
     * 修改赞美诗
     * 
     * @param tSongs 赞美诗
     * @return 结果
     */
    @Override
    public int updateTSongs(TSongs tSongs)
    {
        return tSongsMapper.updateTSongs(tSongs);
    }

    /**
     * 批量删除赞美诗
     * 
     * @param ids 需要删除的赞美诗主键
     * @return 结果
     */
    @Override
    public int deleteTSongsByIds(Long[] ids)
    {
        return tSongsMapper.deleteTSongsByIds(ids);
    }

    /**
     * 删除赞美诗信息
     * 
     * @param id 赞美诗主键
     * @return 结果
     */
    @Override
    public int deleteTSongsById(Long id)
    {
        return tSongsMapper.deleteTSongsById(id);
    }
}
