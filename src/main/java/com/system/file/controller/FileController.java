package com.system.file.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.system.dictionary.bean.Dictionary;

@Controller
public class FileController {
    
    @RequestMapping("file/upload.do")
    public  String redirect(HttpServletRequest request){
        return "/jsp/system/file/showFile";
    }
    
    @RequestMapping(value = "file/uploadAjax", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, ModelMap model) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(file.getSize()<=0){
            map.put("success", "请重新选择上传文件!");
            return map;
        }
        String filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/"; 
        SimpleDateFormat  df = new SimpleDateFormat("yyyyMMdd_HHmmssE_");
        String fileName = df.format(new Date())+file.getOriginalFilename();
        File targetFile = new File(filePath, fileName);
        
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
        
        map.put("success", file.getOriginalFilename());
        return map;
    }
}
