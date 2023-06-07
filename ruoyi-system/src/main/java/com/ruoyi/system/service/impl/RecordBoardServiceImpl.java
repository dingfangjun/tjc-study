package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RecordBoardMapper;
import com.ruoyi.system.domain.RecordBoard;
import com.ruoyi.system.service.IRecordBoardService;

/**
 * 留言板Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
@Service
public class RecordBoardServiceImpl implements IRecordBoardService 
{
    @Autowired
    private RecordBoardMapper recordBoardMapper;

    /**
     * 查询留言板
     * 
     * @param id 留言板主键
     * @return 留言板
     */
    @Override
    public RecordBoard selectRecordBoardById(Long id)
    {
        return recordBoardMapper.selectRecordBoardById(id);
    }

    /**
     * 查询留言板列表
     * 
     * @param recordBoard 留言板
     * @return 留言板
     */
    @Override
    public List<RecordBoard> selectRecordBoardList(RecordBoard recordBoard)
    {
        return recordBoardMapper.selectRecordBoardList(recordBoard);
    }

    /**
     * 新增留言板
     * 
     * @param recordBoard 留言板
     * @return 结果
     */
    @Override
    public int insertRecordBoard(RecordBoard recordBoard)
    {
        return recordBoardMapper.insertRecordBoard(recordBoard);
    }

    /**
     * 修改留言板
     * 
     * @param recordBoard 留言板
     * @return 结果
     */
    @Override
    public int updateRecordBoard(RecordBoard recordBoard)
    {
        return recordBoardMapper.updateRecordBoard(recordBoard);
    }

    /**
     * 批量删除留言板
     * 
     * @param ids 需要删除的留言板主键
     * @return 结果
     */
    @Override
    public int deleteRecordBoardByIds(Long[] ids)
    {
        return recordBoardMapper.deleteRecordBoardByIds(ids);
    }

    /**
     * 删除留言板信息
     * 
     * @param id 留言板主键
     * @return 结果
     */
    @Override
    public int deleteRecordBoardById(Long id)
    {
        return recordBoardMapper.deleteRecordBoardById(id);
    }
}
