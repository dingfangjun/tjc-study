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
import com.ruoyi.system.domain.TFriendSquare;
import com.ruoyi.system.service.ITFriendSquareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 广场Controller
 * 
 * @author ruoyi
 * @date 2023-05-28
 */
@RestController
@RequestMapping("/system/square")
public class TFriendSquareController extends BaseController
{
    @Autowired
    private ITFriendSquareService tFriendSquareService;

    /**
     * 查询广场列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TFriendSquare tFriendSquare)
    {
        startPage();
        List<TFriendSquare> list = tFriendSquareService.selectTFriendSquareList(tFriendSquare);
        return getDataTable(list);
    }

    /**
     * 导出广场列表
     */
    @Log(title = "广场", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TFriendSquare tFriendSquare)
    {
        List<TFriendSquare> list = tFriendSquareService.selectTFriendSquareList(tFriendSquare);
        ExcelUtil<TFriendSquare> util = new ExcelUtil<TFriendSquare>(TFriendSquare.class);
        util.exportExcel(response, list, "广场数据");
    }

    /**
     * 获取广场详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(tFriendSquareService.selectTFriendSquareById(id));
    }

    /**
     * 新增广场
     */
    @Log(title = "广场", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TFriendSquare tFriendSquare)
    {
        return toAjax(tFriendSquareService.insertTFriendSquare(tFriendSquare));
    }

    /**
     * 修改广场
     */
    @Log(title = "广场", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TFriendSquare tFriendSquare)
    {
        return toAjax(tFriendSquareService.updateTFriendSquare(tFriendSquare));
    }

    /**
     * 删除广场
     */
    @Log(title = "广场", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(tFriendSquareService.deleteTFriendSquareByIds(ids));
    }
}
