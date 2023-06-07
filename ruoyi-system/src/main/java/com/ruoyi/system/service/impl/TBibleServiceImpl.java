package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TBibleMapper;
import com.ruoyi.system.domain.TBible;
import com.ruoyi.system.service.ITBibleService;

/**
 * 圣经Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-25
 */
@Service
public class TBibleServiceImpl implements ITBibleService 
{
    @Autowired
    private TBibleMapper tBibleMapper;

    /**
     * 查询圣经
     * 
     * @param id 圣经主键
     * @return 圣经
     */
    @Override
    public TBible selectTBibleById(Integer id)
    {
        return tBibleMapper.selectTBibleById(id);
    }

    /**
     * 查询圣经列表
     * 
     * @param tBible 圣经
     * @return 圣经
     */
    @Override
    public List<TBible> selectTBibleList(TBible tBible)
    {
        return tBibleMapper.selectTBibleList(tBible);
    }

    /**
     * 新增圣经
     * 
     * @param tBible 圣经
     * @return 结果
     */
    @Override
    public int insertTBible(TBible tBible)
    {
        return tBibleMapper.insertTBible(tBible);
    }

    /**
     * 修改圣经
     * 
     * @param tBible 圣经
     * @return 结果
     */
    @Override
    public int updateTBible(TBible tBible)
    {
        return tBibleMapper.updateTBible(tBible);
    }

    /**
     * 批量删除圣经
     * 
     * @param ids 需要删除的圣经主键
     * @return 结果
     */
    @Override
    public int deleteTBibleByIds(Integer[] ids)
    {
        return tBibleMapper.deleteTBibleByIds(ids);
    }

    /**
     * 删除圣经信息
     * 
     * @param id 圣经主键
     * @return 结果
     */
    @Override
    public int deleteTBibleById(Integer id)
    {
        return tBibleMapper.deleteTBibleById(id);
    }
}
