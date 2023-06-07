package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.RecordBoard;

/**
 * 留言板Service接口
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
public interface IRecordBoardService 
{
    /**
     * 查询留言板
     * 
     * @param id 留言板主键
     * @return 留言板
     */
    public RecordBoard selectRecordBoardById(Long id);

    /**
     * 查询留言板列表
     * 
     * @param recordBoard 留言板
     * @return 留言板集合
     */
    public List<RecordBoard> selectRecordBoardList(RecordBoard recordBoard);

    /**
     * 新增留言板
     * 
     * @param recordBoard 留言板
     * @return 结果
     */
    public int insertRecordBoard(RecordBoard recordBoard);

    /**
     * 修改留言板
     * 
     * @param recordBoard 留言板
     * @return 结果
     */
    public int updateRecordBoard(RecordBoard recordBoard);

    /**
     * 批量删除留言板
     * 
     * @param ids 需要删除的留言板主键集合
     * @return 结果
     */
    public int deleteRecordBoardByIds(Long[] ids);

    /**
     * 删除留言板信息
     * 
     * @param id 留言板主键
     * @return 结果
     */
    public int deleteRecordBoardById(Long id);
}
