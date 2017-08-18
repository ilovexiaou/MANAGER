package com.system.template.process.controller;

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

import com.system.dictionary.bean.Dictionary;
import com.system.dictionary.service.DictionaryService;
import com.system.template.process.bean.ProcessTemplate;
import com.system.template.process.bean.ProcessTemplateForm;
import com.system.template.process.service.ProcessTemplateService;
import com.utils.json.JsonUtils;
@Controller
public class ProcessTemplateController {
    @Resource
    ProcessTemplateService processTemplateService;
    @Resource
    DictionaryService dictionaryService;
    
    @RequestMapping("template/process/getAll.do")
    public  String listAll(){
        return "/jsp/system/template/process/showTemplate";
    }
    
    @RequestMapping(value = "template/process/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<ProcessTemplate> processTemplateList;
        Map<String, Object> map;
        int count=processTemplateService.countProcess();
        processTemplateList=processTemplateService.getPageProcess(offset, limit);
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", processTemplateList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("template/process/addTemplate.do")
    public String add(HttpServletRequest request){
        List<Dictionary> list = dictionaryService.getselectCondition("审批类型", null, 0, 10);
        request.setAttribute("kindList", list);
        return "/jsp/system/template/process/addTemplate";
    }
    
    @RequestMapping("template/process/saveTemplate.do")
    public String save(ProcessTemplateForm processTemplateForm,final RedirectAttributes redirectAttributes){
        processTemplateService.saveProcess(processTemplateForm);
        redirectAttributes.addFlashAttribute("message", "成功建立审批模板！");
        return "redirect:getAll.do";
    }
    
    @RequestMapping("template/process/lookupTemplate.do")
    public String lookup(@RequestParam("id") int id,HttpServletRequest request){
        List<Object> list = processTemplateService.getConditionProcess(id);
        request.setAttribute("pt", list.get(0));
        request.setAttribute("list", list.get(1));
        return "/jsp/system/template/process/lookupTemplate";
    }
    
    @RequestMapping("template/process/deleteTemplate.do")
    public String delete(@RequestParam("id") int id,final RedirectAttributes redirectAttributes){
        processTemplateService.deleteProcess(id);
        redirectAttributes.addFlashAttribute("message", "成功删除审批模板！");
        return "redirect:getAll.do";
    }
}
