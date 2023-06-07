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
import com.ruoyi.system.domain.TEverydayRecords;
import com.ruoyi.system.service.ITEverydayRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 每日茶经Controller
 * 
 * @author ruoyi
 * @date 2022-04-24
 */
@RestController
@RequestMapping("/system/records")
public class TEverydayRecordsController extends BaseController
{
    @Autowired
    private ITEverydayRecordsService tEverydayRecordsService;

    /**
     * 查询每日茶经列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEverydayRecords tEverydayRecords)
    {
        startPage();
        List<TEverydayRecords> list = tEverydayRecordsService.selectTEverydayRecordsList(tEverydayRecords);
        return getDataTable(list);
    }
    /**
     * 查询每日茶经列表
     */
    @GetMapping("/listByMonth")
    public TableDataInfo listByMonth(TEverydayRecords tEverydayRecords)
    {
        startPage();
        List<TEverydayRecords> list = tEverydayRecordsService.selectTEverydayRecordsListByMonth(tEverydayRecords);
        return getDataTable(list);
    }
    /**
     * 导出每日茶经列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:export')")
    @Log(title = "每日茶经", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TEverydayRecords tEverydayRecords)
    {
        List<TEverydayRecords> list = tEverydayRecordsService.selectTEverydayRecordsList(tEverydayRecords);
        ExcelUtil<TEverydayRecords> util = new ExcelUtil<TEverydayRecords>(TEverydayRecords.class);
        util.exportExcel(response, list, "每日茶经数据");
    }

    /**
     * 获取每日茶经详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tEverydayRecordsService.selectTEverydayRecordsById(id));
    }

    /**
     * 新增每日茶经
     */
    @PreAuthorize("@ss.hasPermi('system:records:add')")
    @Log(title = "每日茶经", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TEverydayRecords tEverydayRecords)
    {
        return toAjax(tEverydayRecordsService.insertTEverydayRecords(tEverydayRecords));
    }

    /**
     * 修改每日茶经
     */
    @PreAuthorize("@ss.hasPermi('system:records:edit')")
    @Log(title = "每日茶经", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TEverydayRecords tEverydayRecords)
    {
        return toAjax(tEverydayRecordsService.updateTEverydayRecords(tEverydayRecords));
    }

    /**
     * 删除每日茶经
     */
    @PreAuthorize("@ss.hasPermi('system:records:remove')")
    @Log(title = "每日茶经", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tEverydayRecordsService.deleteTEverydayRecordsByIds(ids));
    }
}
