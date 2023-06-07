package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 赞美诗对象 t_songs
 * 
 * @author ruoyi
 * @date 2023-05-27
 */
public class TSongs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 序号 */
    @Excel(name = "序号")
    private String sortNo;

    /** 歌名 */
    @Excel(name = "歌名")
    private String name;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSortNo(String sortNo) 
    {
        this.sortNo = sortNo;
    }

    public String getSortNo() 
    {
        return sortNo;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sortNo", getSortNo())
            .append("name", getName())
            .append("content", getContent())
            .toString();
    }
}
