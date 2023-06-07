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
import com.ruoyi.system.domain.TSongs;
import com.ruoyi.system.service.ITSongsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赞美诗Controller
 * 
 * @author ruoyi
 * @date 2023-05-27
 */
@RestController
@RequestMapping("/system/songs")
public class TSongsController extends BaseController
{
    @Autowired
    private ITSongsService tSongsService;

    /**
     * 查询赞美诗列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TSongs tSongs)
    {
        startPage();
        List<TSongs> list = tSongsService.selectTSongsList(tSongs);
        return getDataTable(list);
    }

    /**
     * 导出赞美诗列表
     */
    @Log(title = "赞美诗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSongs tSongs)
    {
        List<TSongs> list = tSongsService.selectTSongsList(tSongs);
        ExcelUtil<TSongs> util = new ExcelUtil<TSongs>(TSongs.class);
        util.exportExcel(response, list, "赞美诗数据");
    }

    /**
     * 获取赞美诗详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tSongsService.selectTSongsById(id));
    }

    /**
     * 新增赞美诗
     */
    @Log(title = "赞美诗", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSongs tSongs)
    {
        return toAjax(tSongsService.insertTSongs(tSongs));
    }

    /**
     * 修改赞美诗
     */
    @Log(title = "赞美诗", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSongs tSongs)
    {
        return toAjax(tSongsService.updateTSongs(tSongs));
    }

    /**
     * 删除赞美诗
     */
    @Log(title = "赞美诗", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tSongsService.deleteTSongsByIds(ids));
    }
}
