package com.personnel.vacation.controller;

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

import com.personnel.employee.bean.Employee;
import com.personnel.vacation.bean.Vacation;
import com.personnel.vacation.service.VacationService;
import com.system.template.record.bean.AuditRecord;
import com.utils.guava.cache.service.LoadUser;
import com.utils.json.JsonUtils;

@Controller
public class VacationController {
    @Resource
    VacationService vacationService;
    @Resource
    LoadUser loadUser;
    
    @RequestMapping("vacation/getAll.do")
    public  String listAll(){
        return "/jsp/personnel/vacation/showVacation";
    }
    
    @RequestMapping(value = "vacation/ajax.do", method = RequestMethod.POST)
    public void showAll(@RequestParam("createdId") int createdId,HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Vacation> vacationList;
        int count;
        Map<String, Object> map;
        vacationList = vacationService.getMyPageVacation(offset, limit, createdId);
        count = vacationService.countMyPageVacation(createdId);
        
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", vacationList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("vacation/addVacation.do")
    public String add(HttpServletRequest request){
        Employee employee = loadUser.get(10);
        System.out.println(employee.getName());
        return "/jsp/personnel/vacation/addVacation";
    }
    @RequestMapping("vacation/saveVacation.do")
    public String save(Vacation vacation,final RedirectAttributes redirectAttributes){
        vacationService.saveVacation(vacation);
        redirectAttributes.addFlashAttribute("message", "成功提交请假单，请等待审核！");
        return "redirect:getAll.do";
    }
    
    @RequestMapping("vacation/approvalAll.do")
    public  String listApprovalAll(){
        return "/jsp/personnel/vacation/showApprovalVacation";
    }
    @RequestMapping(value = "vacation/approvalajax.do", method = RequestMethod.POST)
    public void showApprovalAll(@RequestParam("createdId") int createdId,HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Vacation> vacationList;
        int count;
        Map<String, Object> map;
        vacationList =vacationService.getApprovalVacation(offset, limit, createdId);
        count =vacationService.countApprovalVacation(createdId);
        
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", vacationList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("vacation/addApprovalVacation.do")
    public String addApproval(@RequestParam("id") int id,HttpServletRequest request){
        List<Object> list = vacationService.getApproval(id);
        request.setAttribute("vacation", list.get(0));
        request.setAttribute("list", list.get(1));
        return "/jsp/personnel/vacation/addApprovalVacation";
    }
    
    @RequestMapping("vacation/updateApprovalVacation.do")
    public String updateApproval(AuditRecord auditRecord,final RedirectAttributes redirectAttributes){
        vacationService.updateApprovalVacation(auditRecord);
        redirectAttributes.addFlashAttribute("message", "成功审核请假单");
        return "redirect:getAll.do";
    }
}
