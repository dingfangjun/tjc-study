package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateUtils;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 实体
 *
 * @author sqwan
 * @date 2023-06-05
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("check_record")
public class CheckRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考勤表id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    public Integer getRecordId() {
        return recordId;
    }

    public CheckRecord setRecordId(Integer recordId) {
        this.recordId = recordId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public CheckRecord setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public CheckRecord setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CheckRecord setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public CheckRecord setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public CheckRecord setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public CheckRecord setRemark(String remark) {
        this.remark = remark;
        return this;
    }

}
