package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TSaturdayLogs;
import com.ruoyi.system.domain.vo.Directory;
import com.ruoyi.system.domain.vo.UserVo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fileManage")
public class FileManageController extends BaseController {
    @RequestMapping(value = "/getFileListByPath",method = RequestMethod.GET)
    @ResponseBody
    public String[] getFileListByPath( @RequestParam("path") String path) throws Exception
    {
              File file = new File(path);

                  String[]  files = file.list();
                  System.out.println(files);
                  return files;
    }
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @RequestParam("filePath") String filePath, @RequestParam("fileName") String fileName) throws Exception {
        String fileNewPath = filePath + fileName;
        System.out.println(fileNewPath);

        InputStream inStream = new FileInputStream(fileNewPath);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/addFile",method = RequestMethod.POST)
    public void addFile( @RequestParam(value="filePath") String filePath, @RequestParam(value="multipartFiles") MultipartFile multipartFile) throws Exception {
                    System.out.println(filePath);
                    File tmpFile = new File(filePath);
                    multipartFile.transferTo(tmpFile);
    }
    @RequestMapping(value = "/reNameFile",method = RequestMethod.GET)
    @ResponseBody
    public Boolean reNameFile( @RequestParam("filePath") String filePath,@RequestParam("newName") String newName) throws Exception
    {
        System.out.println(filePath);
        System.out.println(newName);
        File file = new File(filePath);
        String newPath = file.getParent()+"\\" + newName;
        Boolean res =  file.renameTo(new File(newPath));
        return  res;
    }
    @RequestMapping(value = "/deleteFile",method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteFile( @RequestParam("filePath") String filePath)
    {
        System.out.println("删除文件："+filePath);
        File file = new File(filePath);
        return file.delete();
    }
    @RequestMapping(value = "/moveFileTo",method = RequestMethod.GET)
    @ResponseBody
    public Boolean moveFileTo( @RequestParam("recentPath") String recentPath,@RequestParam("targetPath") String targetPath) throws Exception
    {
        System.out.println(recentPath);
        System.out.println(targetPath);
        File file = new File(recentPath);
        Boolean res =  file.renameTo(new File(targetPath));
        return  res;
    }



}
