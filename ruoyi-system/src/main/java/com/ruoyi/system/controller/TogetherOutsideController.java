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
import com.ruoyi.system.domain.TogetherOutside;
import com.ruoyi.system.service.ITogetherOutsideService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动Controller
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
@RestController
@RequestMapping("/system/outside")
public class TogetherOutsideController extends BaseController
{
    @Autowired
    private ITogetherOutsideService togetherOutsideService;

    /**
     * 查询活动列表
     */
    @PreAuthorize("@ss.hasPermi('system:outside:list')")
    @GetMapping("/list")
    public TableDataInfo list(TogetherOutside togetherOutside)
    {
        startPage();
        List<TogetherOutside> list = togetherOutsideService.selectTogetherOutsideList(togetherOutside);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @PreAuthorize("@ss.hasPermi('system:outside:export')")
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TogetherOutside togetherOutside)
    {
        List<TogetherOutside> list = togetherOutsideService.selectTogetherOutsideList(togetherOutside);
        ExcelUtil<TogetherOutside> util = new ExcelUtil<TogetherOutside>(TogetherOutside.class);
        util.exportExcel(response, list, "活动数据");
    }

    /**
     * 获取活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:outside:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(togetherOutsideService.selectTogetherOutsideById(id));
    }

    /**
     * 新增活动
     */
    @PreAuthorize("@ss.hasPermi('system:outside:add')")
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TogetherOutside togetherOutside)
    {
        return toAjax(togetherOutsideService.insertTogetherOutside(togetherOutside));
    }

    /**
     * 修改活动
     */
    @PreAuthorize("@ss.hasPermi('system:outside:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TogetherOutside togetherOutside)
    {
        return toAjax(togetherOutsideService.updateTogetherOutside(togetherOutside));
    }

    /**
     * 删除活动
     */
    @PreAuthorize("@ss.hasPermi('system:outside:remove')")
    @Log(title = "活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(togetherOutsideService.deleteTogetherOutsideByIds(ids));
    }
}
