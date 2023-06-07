package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 留言板对象 record_board
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
public class RecordBoard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long id;

    /** 留言用户名 */
    @Excel(name = "留言用户名")
    private String userName;

    /** 留言用户别名 */
    @Excel(name = "留言用户别名")
    private String userAnotherName;

    /** 留言内容 */
    @Excel(name = "留言内容")
    private String content;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer agreeCount;

    /** 反对数 */
    @Excel(name = "反对数")
    private Integer disagreeCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserAnotherName(String userAnotherName) 
    {
        this.userAnotherName = userAnotherName;
    }

    public String getUserAnotherName() 
    {
        return userAnotherName;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setAgreeCount(Integer agreeCount) 
    {
        this.agreeCount = agreeCount;
    }

    public Integer getAgreeCount() 
    {
        return agreeCount;
    }
    public void setDisagreeCount(Integer disagreeCount) 
    {
        this.disagreeCount = disagreeCount;
    }

    public Integer getDisagreeCount() 
    {
        return disagreeCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("userAnotherName", getUserAnotherName())
            .append("content", getContent())
            .append("agreeCount", getAgreeCount())
            .append("disagreeCount", getDisagreeCount())
            .toString();
    }
}
