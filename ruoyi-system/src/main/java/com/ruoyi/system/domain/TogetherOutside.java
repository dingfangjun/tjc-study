package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动对象 together_outside
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
public class TogetherOutside extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 计划时间 */
    @Excel(name = "计划时间")
    private String planTime;

    /** 实际日期 */
    @Excel(name = "实际日期")
    private String realTime;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 参与人员 */
    @Excel(name = "参与人员")
    private String userList;

    /** 活动起因 */
    @Excel(name = "活动起因")
    private String reason;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String content;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String imgUrls;

    /** 是否真是举办 */
    @Excel(name = "是否真是举办")
    private String really;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String other;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPlanTime(String planTime) 
    {
        this.planTime = planTime;
    }

    public String getPlanTime() 
    {
        return planTime;
    }
    public void setRealTime(String realTime) 
    {
        this.realTime = realTime;
    }

    public String getRealTime() 
    {
        return realTime;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setUserList(String userList) 
    {
        this.userList = userList;
    }

    public String getUserList() 
    {
        return userList;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setImgUrls(String imgUrls) 
    {
        this.imgUrls = imgUrls;
    }

    public String getImgUrls() 
    {
        return imgUrls;
    }
    public void setReally(String really) 
    {
        this.really = really;
    }

    public String getReally() 
    {
        return really;
    }
    public void setOther(String other) 
    {
        this.other = other;
    }

    public String getOther() 
    {
        return other;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planTime", getPlanTime())
            .append("realTime", getRealTime())
            .append("address", getAddress())
            .append("userList", getUserList())
            .append("reason", getReason())
            .append("content", getContent())
            .append("imgUrls", getImgUrls())
            .append("really", getReally())
            .append("other", getOther())
            .toString();
    }
}
