package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TBible;

/**
 * 圣经Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-25
 */
public interface TBibleMapper 
{
    /**
     * 查询圣经
     * 
     * @param id 圣经主键
     * @return 圣经
     */
    public TBible selectTBibleById(Integer id);

    /**
     * 查询圣经列表
     * 
     * @param tBible 圣经
     * @return 圣经集合
     */
    public List<TBible> selectTBibleList(TBible tBible);

    /**
     * 新增圣经
     * 
     * @param tBible 圣经
     * @return 结果
     */
    public int insertTBible(TBible tBible);

    /**
     * 修改圣经
     * 
     * @param tBible 圣经
     * @return 结果
     */
    public int updateTBible(TBible tBible);

    /**
     * 删除圣经
     * 
     * @param id 圣经主键
     * @return 结果
     */
    public int deleteTBibleById(Integer id);

    /**
     * 批量删除圣经
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBibleByIds(Integer[] ids);
}
