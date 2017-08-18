package com.personnel.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.personnel.employee.bean.Employee;
import com.personnel.employee.service.EmployeeService;
import com.utils.json.JsonUtils;

@Controller
public class EmployeeController {
    @Resource
    EmployeeService employeeService;
    /**
     * 跳转员工页面，初始化页面
     * @param model
     * @return
     */
    @RequestMapping("employee/getAll.do")
    public  String listAll(Model model,HttpServletRequest request){
        return "/jsp/personnel/employee/showEmployee";
    }
    /**
     * 分页显示所有员工
     * @param response
     * @param request
     */
    @RequestMapping(value = "employee/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request) {
        String flag = request.getParameter("enabled");//选择员工iframe，页面判断
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Employee> personList;
        int count;
        Map<String, Object> map;
        if(null!=flag && flag!=""){//启用的员工查询
            personList = employeeService.getPagingAll(offset,limit,1);
            count = employeeService.countAllEmployee(1);
        }else{//所有员工查询
            personList = employeeService.getPagingAll(offset,limit,null);
            count = employeeService.countAllEmployee(null);
        }
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", personList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 按条件搜索员工
     * @param response
     * @param request
     */
    @RequestMapping(value = "employee/selectEmployee.do", method = RequestMethod.POST)
    public void selectEmployee(HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String idCard = request.getParameter("idCard");
        String degree = request.getParameter("degree");
        String flag = request.getParameter("flag");
        String departmentId = request.getParameter("departmentId");
        String inDate = request.getParameter("inDate");
        String outDate = request.getParameter("outDate");
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("offset", offset);
        map.put("limit", limit);
        map.put("name", name);
        map.put("number", number);
        map.put("idCard", idCard);
        map.put("degree", degree);
        map.put("flag", flag);
        map.put("departmentId", departmentId);
        map.put("inDate", inDate);
        map.put("outDate", outDate);
        List<Employee> list = employeeService.selectConditionEmployee(map);
        int count=employeeService.countConditionEmployee(map);
        //前台分页 json 格式
        Map<String, Object> resultMap=new HashMap<String, Object>();
        resultMap.put("total", count);
        resultMap.put("rows", list);
        try {
            JsonUtils.convertListToJson(resultMap, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化添加员工页面
     * @param model
     * @return
     */
    @RequestMapping("employee/addEmployee.do")
    public  String addEmployee(Model model){
        return "/jsp/personnel/employee/addEmployee";
    }
    /**
     * 保存新建员工
     * @param model
     * @return
     */
    @RequestMapping(value="employee/saveEmployee.do", method=RequestMethod.POST)
    public  String  saveEmployee(Employee employee,final RedirectAttributes redirectAttributes){
        try {
            employeeService.saveEmployee(employee);
            redirectAttributes.addFlashAttribute("message", "成功添加新员工！");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "添加新员工失败！");
        }
        return "redirect:getAll.do";
    }
    /**
     * 删除
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="employee/deleteEmployee.do", method=RequestMethod.GET)
    public  String  deleteEmployee(@RequestParam("id") String id,final RedirectAttributes redirectAttributes){
        try {
            employeeService.deleteEmployee(Integer.valueOf(id));
            redirectAttributes.addFlashAttribute("message", "成功删除员工！");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "删除员工失败！");
        }
        return "redirect:getAll.do";
    }
    /**
     * 跳转修改页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value="employee/editEmployeeById.do", method=RequestMethod.GET)
    public  String  editEmployeeById(@RequestParam("id") String id,HttpServletRequest request){
        Employee employee = employeeService.selectEmployeeById(Integer.valueOf(id));
        request.setAttribute("employee", employee);
        return "/jsp/personnel/employee/editEmployee";
    }
    /**
     * 跳转查看页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value="employee/lookupEmployeeById.do", method=RequestMethod.GET)
    public  String  lookupEmployeeById(@RequestParam("id") String id,HttpServletRequest request){
        Employee employee = employeeService.selectEmployeeById(Integer.valueOf(id));
        request.setAttribute("employee", employee);
        return "/jsp/personnel/employee/lookupEmployee";
    }
    /**
     * 更新员工
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value="employee/updateEmployee.do", method=RequestMethod.POST)
    public  String  updateEmployeeById(Employee employee,HttpServletRequest request,final RedirectAttributes redirectAttributes){
        try {
            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("message", "成功修改员工！");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "修改员工失败！");
        }
        return "redirect:getAll.do";
    }
    /**
     * iframe里的搜索
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("employee/selectAllEmployee.do")
    public  String listSelectEmployee(Model model,HttpServletRequest request){
        return "/jsp/personnel/employee/selectEmployee";
    }
    
    @RequestMapping("employee/printEmployee.do")
    public  String printEmployee(@RequestParam("id") String id,HttpServletRequest request){
        Employee employee = employeeService.selectEmployeeById(Integer.valueOf(id));
        request.setAttribute("employee", employee);
        return "/jsp/personnel/employee/printEmployee";
    }
}
