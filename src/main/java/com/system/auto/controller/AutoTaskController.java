package com.system.auto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.auto.service.AutoTaskService;
import com.utils.json.JsonUtils;
import com.utils.quartz.bean.AutoTask;

@Controller
public class AutoTaskController {
    @Resource
    AutoTaskService autoTaskService;
    
    @RequestMapping("autoTask/getAll.do")
    public  String listAll(){
        return "/jsp/system/autoTask/showAutoTask";
    }
    
    
    @RequestMapping(value = "autoTask/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<AutoTask> dictionaryList;
        int count;
        Map<String, Object> map;
        dictionaryList = autoTaskService.getAllbyPaging(offset, limit);
        count = autoTaskService.countAll();
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", dictionaryList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("autoTask/addAutoTask.do")
    public  String add(){
        return "/jsp/system/autoTask/addAutoTask";
    }
    
    @RequestMapping(value = "autoTask/saveAutoTask.do", method = RequestMethod.POST)
    public  String save(AutoTask autoTask,final RedirectAttributes redirectAttributes){
        try {
            autoTaskService.saveAutoTask(autoTask);
            redirectAttributes.addFlashAttribute("message", "成功新建自动任务！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "新建自动任务失败！");
        }
        return "redirect:getAll.do";
    }
    
    @RequestMapping("autoTask/deleteAutoTask.do")
    public String delete(@RequestParam("id") int id,final RedirectAttributes redirectAttributes){
        try {
            autoTaskService.deleteAutoTask(id);
            redirectAttributes.addFlashAttribute("message", "成功删除自动任务！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "自动任务删除失败！");
        }
        return "redirect:getAll.do";
    }
}
