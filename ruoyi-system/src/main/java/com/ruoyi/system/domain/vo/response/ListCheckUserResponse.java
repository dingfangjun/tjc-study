package com.ruoyi.system.domain.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author sqwan
 * @Description 查询审核记录
 * @Date 2023/6/7 14:36
 * @Version 1.0
 */
@ApiModel("查询审核记录返回值")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ListCheckUserResponse {


    @ApiModelProperty("部门id")
    private Integer deptId;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("片区人员")
    private List<CheckUser> checkUserList;

    public static class CheckUser {
        @ApiModelProperty("用户id")
        private Integer userId;

        @ApiModelProperty("用户名称")
        private String userName;

        @ApiModelProperty("考勤次数")
        private Integer count;

        @ApiModelProperty("考勤时间")
        private List<Record> records;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Record> getRecords() {
            return records;
        }

        public void setRecords(List<Record> records) {
            this.records = records;
        }
    }

    public static class Record {
        @ApiModelProperty("考勤时间")
        @JsonFormat(timezone = "GMT+8", pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
        private LocalDateTime checkTime;

        public LocalDateTime getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(LocalDateTime checkTime) {
            this.checkTime = checkTime;
        }
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<CheckUser> getCheckUserList() {
        return checkUserList;
    }

    public void setCheckUserList(List<CheckUser> checkUserList) {
        this.checkUserList = checkUserList;
    }
}
