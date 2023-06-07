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
import com.ruoyi.system.domain.RecordBoard;
import com.ruoyi.system.service.IRecordBoardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 留言板Controller
 * 
 * @author ruoyi
 * @date 2022-06-30
 */
@RestController
@RequestMapping("/system/record_board")
public class RecordBoardController extends BaseController
{
    @Autowired
    private IRecordBoardService recordBoardService;

    /**
     * 查询留言板列表
     */
    @PreAuthorize("@ss.hasPermi('system:record_board:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecordBoard recordBoard)
    {
        startPage();
        List<RecordBoard> list = recordBoardService.selectRecordBoardList(recordBoard);
        return getDataTable(list);
    }

    /**
     * 导出留言板列表
     */
    @PreAuthorize("@ss.hasPermi('system:record_board:export')")
    @Log(title = "留言板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecordBoard recordBoard)
    {
        List<RecordBoard> list = recordBoardService.selectRecordBoardList(recordBoard);
        ExcelUtil<RecordBoard> util = new ExcelUtil<RecordBoard>(RecordBoard.class);
        util.exportExcel(response, list, "留言板数据");
    }

    /**
     * 获取留言板详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record_board:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(recordBoardService.selectRecordBoardById(id));
    }

    /**
     * 新增留言板
     */
    @PreAuthorize("@ss.hasPermi('system:record_board:add')")
    @Log(title = "留言板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecordBoard recordBoard)
    {
        return toAjax(recordBoardService.insertRecordBoard(recordBoard));
    }

    /**
     * 修改留言板
     */
    @PreAuthorize("@ss.hasPermi('system:record_board:edit')")
    @Log(title = "留言板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecordBoard recordBoard)
    {
        return toAjax(recordBoardService.updateRecordBoard(recordBoard));
    }

    /**
     * 删除留言板
     */
    @PreAuthorize("@ss.hasPermi('system:record_board:remove')")
    @Log(title = "留言板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(recordBoardService.deleteRecordBoardByIds(ids));
    }
}
