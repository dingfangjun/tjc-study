package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 每日茶经对象 t_everyday_records
 * 
 * @author ruoyi
 * @date 2022-04-24
 */
public class TEverydayRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 日期 */
    @Excel(name = "日期")
    private String date;

    /** 打卡人员 */
    @Excel(name = "打卡人员")
    private String userlist;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDate(String date) 
    {
        this.date = date;
    }

    public String getDate() 
    {
        return date;
    }
    public void setUserlist(String userlist) 
    {
        this.userlist = userlist;
    }

    public String getUserlist() 
    {
        return userlist;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("date", getDate())
            .append("userlist", getUserlist())
            .toString();
    }
}
