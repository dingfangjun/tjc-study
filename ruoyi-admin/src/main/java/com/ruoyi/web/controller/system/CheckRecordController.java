package com.ruoyi.web.controller.system;


import com.alibaba.druid.support.json.JSONUtils;
import com.ruoyi.common.constant.ServerConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.BeanValidators;
import com.ruoyi.system.domain.CheckRecord;
import com.ruoyi.system.service.CheckRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * 前端控制器
 *
 * @author sqwan
 * @since 2023-06-05
 */
@Slf4j
@RestController
@RequestMapping("/check-record")
public class CheckRecordController extends BaseController {
    @Autowired
    private CheckRecordService service;
    @Autowired
    private Validator validator;

    /**
     * 分页列表
     *
     * @return com.keyuan.api.bo.ResponseMessage
     * @author sqwan
     * @date 2023-06-05
     */
    @GetMapping
    public AjaxResult page() {
        startPage();
        List<CheckRecord> page = this.service.list();
        return AjaxResult.success(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return com.keyuan.api.bo.ResponseMessage
     * @author sqwan
     * @date 2023-06-05
     */
    @GetMapping(value = "/{id}")
    public AjaxResult get(@PathVariable("id") String id) {
        CheckRecord entity = null;
        entity = this.service.getById(id);
        return AjaxResult.success(entity);
    }

    /**
     * 保存
     *
     * @param entity 数据（json格式）
     * @return com.keyuan.api.bo.ResponseMessage
     * @author sqwan
     * @date 2023-06-05
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult create(@RequestBody CheckRecord entity) {
        //调用Validator进行校验，如果出错返回json格式的错误信息.
        Set<ConstraintViolation<CheckRecord>> failures = validator.validate(entity);
        if (!failures.isEmpty()) {
            return AjaxResult.error(JSONUtils.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        this.service.save(entity);
        return AjaxResult.success();
    }

    /**
     * 更新
     *
     * @param entity 数据（json格式）
     * @return com.keyuan.api.bo.ResponseMessage
     * @author sqwan
     * @date 2023-06-05
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult update(@RequestBody CheckRecord entity) {
        //调用Validator进行校验，如果出错返回json格式的错误信息.
        Set<ConstraintViolation<CheckRecord>> failures = validator.validate(entity);
        if (!failures.isEmpty()) {
            return AjaxResult.error(JSONUtils.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        return toAjax(this.service.updateById(entity));
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return com.keyuan.api.bo.ResponseMessage
     * @author sqwan
     * @date 2023-06-05
     */
    @DeleteMapping(value = "/{id}")
    public AjaxResult delete(@PathVariable("id") String id) {
        return toAjax(this.service.removeById(id));
    }

    /**
     * 签到
     *
     * @param userId 用户id
     * @return com.keyuan.api.bo.ResponseMessage
     * @author sqwan
     * @date 2023-06-05
     */
    @PutMapping("check")
    public AjaxResult check(@RequestParam Integer userId) {
        //查看用户上次考勤时间
        CheckRecord checkRecord = this.service.lambdaQuery()
                .select(CheckRecord::getCreateTime)
                .eq(CheckRecord::getUserId, userId)
                .orderByDesc(CheckRecord::getCreateTime)
                .last("limit 1")
                .one();
        if (!Objects.isNull(checkRecord)) {
            //是否超过一个星期
            long day = Duration.between(checkRecord.getCreateTime(), LocalDateTime.now()).toDays();
            if (day < 7) {
                return AjaxResult.warn(ServerConstants.REPEAT_CHECK);
            }
        }
        CheckRecord record = CheckRecord.builder().userId(userId).build();
        return AjaxResult.success(this.service.save(record));
    }

}
