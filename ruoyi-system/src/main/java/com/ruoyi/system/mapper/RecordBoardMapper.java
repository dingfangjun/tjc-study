package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.RecordBoard;

/**
 * 留言板Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
public interface RecordBoardMapper 
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
     * 删除留言板
     * 
     * @param id 留言板主键
     * @return 结果
     */
    public int deleteRecordBoardById(Long id);

    /**
     * 批量删除留言板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRecordBoardByIds(Long[] ids);
}
