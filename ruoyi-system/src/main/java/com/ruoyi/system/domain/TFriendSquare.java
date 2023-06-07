package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 广场对象 t_friend_square
 * 
 * @author ruoyi
 * @date 2023-05-28
 */
public class TFriendSquare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 标题 */
    @Excel(name = "标题")
    private String name;

    /** 时间 */
    @Excel(name = "时间")
    private String time;

    /** 创建者 */
    @Excel(name = "创建者")
    private String creater;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String imgUrl;

    /** 备用 */
    @Excel(name = "备用")
    private String tips;

    /** 备用2 */
    @Excel(name = "备用2")
    private String tips2;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTime(String time) 
    {
        this.time = time;
    }

    public String getTime() 
    {
        return time;
    }
    public void setCreater(String creater) 
    {
        this.creater = creater;
    }

    public String getCreater() 
    {
        return creater;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setTips(String tips) 
    {
        this.tips = tips;
    }

    public String getTips() 
    {
        return tips;
    }
    public void setTips2(String tips2) 
    {
        this.tips2 = tips2;
    }

    public String getTips2() 
    {
        return tips2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("time", getTime())
            .append("creater", getCreater())
            .append("content", getContent())
            .append("imgUrl", getImgUrl())
            .append("tips", getTips())
            .append("tips2", getTips2())
            .toString();
    }
}
