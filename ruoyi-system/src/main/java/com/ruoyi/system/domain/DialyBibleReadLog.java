package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 每日读经对象 dialy_bible_read_log
 * 
 * @author 丁方军
 * @date 2022-04-17
 */
public class DialyBibleReadLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String 签到人员;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String 日期;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String 备注;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void set签到人员(String 签到人员) 
    {
        this.签到人员 = 签到人员;
    }

    public String get签到人员() 
    {
        return 签到人员;
    }
    public void set日期(String 日期) 
    {
        this.日期 = 日期;
    }

    public String get日期() 
    {
        return 日期;
    }
    public void set备注(String 备注) 
    {
        this.备注 = 备注;
    }

    public String get备注() 
    {
        return 备注;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("签到人员", get签到人员())
            .append("日期", get日期())
            .append("备注", get备注())
            .toString();
    }
}
