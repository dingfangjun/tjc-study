package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TActivity;
import com.ruoyi.system.service.ITActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动Controller
 * 
 * @author 丁方军
 * @date 2023-05-24
 */
@RestController
@RequestMapping("/system/activity")
public class TActivityController extends BaseController
{
    @Autowired
    private ITActivityService tActivityService;

    /**
     * 查询活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TActivity tActivity)
    {
        startPage();
        List<TActivity> list = tActivityService.selectTActivityList(tActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TActivity tActivity)
    {
        List<TActivity> list = tActivityService.selectTActivityList(tActivity);
        ExcelUtil<TActivity> util = new ExcelUtil<TActivity>(TActivity.class);
        util.exportExcel(response, list, "活动数据");
    }

    /**
     * 获取活动详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tActivityService.selectTActivityById(id));
    }

    /**
     * 新增活动
     */
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TActivity tActivity)
    {
        return toAjax(tActivityService.insertTActivity(tActivity));
    }

    /**
     * 修改活动
     */
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TActivity tActivity)
    {
        return toAjax(tActivityService.updateTActivity(tActivity));
    }

    /**
     * 删除活动
     */
    @Log(title = "活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tActivityService.deleteTActivityByIds(ids));
    }
}
