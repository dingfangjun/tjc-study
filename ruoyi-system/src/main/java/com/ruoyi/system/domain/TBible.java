package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 圣经对象 t_bible
 * 
 * @author ruoyi
 * @date 2023-05-25
 */
public class TBible extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 书名 */
    @Excel(name = "书名")
    private String chapterName;

    /** 章节 */
    @Excel(name = "章节")
    private String sectionNo;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setChapterName(String chapterName) 
    {
        this.chapterName = chapterName;
    }

    public String getChapterName() 
    {
        return chapterName;
    }
    public void setSectionNo(String sectionNo) 
    {
        this.sectionNo = sectionNo;
    }

    public String getSectionNo() 
    {
        return sectionNo;
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
            .append("chapterName", getChapterName())
            .append("sectionNo", getSectionNo())
            .append("content", getContent())
            .toString();
    }
}
