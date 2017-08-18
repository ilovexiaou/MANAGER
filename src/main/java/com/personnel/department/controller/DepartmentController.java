package com.personnel.department.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.base.constant.Constant;
import com.personnel.department.bean.Department;
import com.personnel.department.bean.DepartmentCondition;
import com.personnel.department.service.DepartmentService;
import com.utils.json.JsonUtils;

@Controller
public class DepartmentController {
    @Resource
    DepartmentService departmentService;
    /**
     * 跳转到组织结构的页面
     * @param model
     * @return
     */
    @RequestMapping("department/getAll.do")
    public  String redirect(Model model){
        return "/jsp/personnel/department/showDepartment";
    }
    /**
     * 展示所有的组织结构，包括禁用的，用json输出到页面
     * @param response
     */
    @RequestMapping(value = "department/ajax.do", method = RequestMethod.GET)
    public void showAll(HttpServletResponse response) {
        List<Department> list = departmentService.getAll();
        Map<String, List<Department>> map=new HashMap<String, List<Department>>();
        map.put("rows", list);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 新加部门
     * @param response
     * @param request
     */
    @RequestMapping(value = "department/add.do", method = RequestMethod.POST)
    public void addDepartment(HttpServletResponse response,HttpServletRequest request) {
        Department dt = new Department();
        String parentId = request.getParameter("parentId");
        String parentName = request.getParameter("parentName");
        String rootName = request.getParameter("rootName");
        dt.setParentName(parentName);
        dt.set_parentId(Integer.valueOf(parentId));
        dt.setRootName(rootName);
        dt.setName(Constant.DEPARTMENT_NAME);
        dt.setFlag(false);
        dt.setSequence(Constant.ZERO);
        departmentService.addDepartment(dt);
        try {
            JsonUtils.convertListToJson(dt, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 添加公司
     * @param response
     * @param request
     */
    @RequestMapping(value = "department/addCompany.do", method = RequestMethod.POST)
    public void addCompany(HttpServletResponse response,HttpServletRequest request) {
        Department dt = new Department();
        dt.setName(Constant.COMPANY_NAME);
        dt.setFlag(false);
        dt.setSequence(Constant.ZERO);
        departmentService.addDepartment(dt);
        try {
            JsonUtils.convertListToJson(dt, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除
     * @param response
     * @param request
     */
    @RequestMapping(value = "department/delete.do", method = RequestMethod.POST)
    public void deleteDepartment(HttpServletResponse response,HttpServletRequest request){
        String deleteids = request.getParameter("deleteids");
        if(null!=deleteids && !"".equals(deleteids)){
            String[] ids = deleteids.split(",");
            for (String id : ids) {
                departmentService.deleteDepartment(Integer.valueOf(id));
            }
        }
        List<String> list = new ArrayList<String>();
        list.add("success");
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 更新
     * @param response
     * @param request
     */
    @RequestMapping(value = "department/update.do", method = RequestMethod.POST)
    public void updateDepartment(HttpServletResponse response,HttpServletRequest request){
        Department dt = new Department();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String flag = request.getParameter("flag");
        String sequence = request.getParameter("sequence");
        dt.setId(Integer.valueOf(id));
        dt.setName(name);
        dt.setSequence(sequence);
        if(flag.equals(Constant.TRUE))dt.setFlag(true);
        departmentService.updateDepartment(dt);
        List<String> list = new ArrayList<String>();
        list.add("success");
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 把所有公司部门 ，以json形式输出到前台select里
     * @param response
     * @param request
     */
    @RequestMapping(value = "department/selectDepartmentCondition.do", method = RequestMethod.GET)
    public void selectDepartmentCondition(HttpServletResponse response,HttpServletRequest request){
        List<Department> list = departmentService.getSelect();
        List<DepartmentCondition> result = new ArrayList<DepartmentCondition>();
        for (Department department : list) {
            DepartmentCondition dc = new DepartmentCondition();
            dc.setId(department.getId());
            dc.setText(department.getName());
            Integer get_parentId = department.get_parentId();
            if(null == get_parentId){
                DepartmentCondition generateTreeNode = this.generateTreeNode(dc.getId(), list);
                result.add(generateTreeNode);
            }
        }
        try {
            JsonUtils.convertListToJson(result, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据id搜索出list里的数据
     * @param nodeId
     * @param list
     * @return
     */
    private Department getNodeById(int nodeId,List<Department> list){
        Department department = new Department();
        for (Department dt : list) {
            if (dt.getId() == nodeId) {
                department = dt;
            break;
            }
        }
        return department;
    }
    /**
     * 根据父节点id，搜索出他下一等级所有的子节点数据
     * @param nodeId
     * @param list
     * @return
     */
    private List<Department> getChildrenNodeById(int nodeId,List<Department> list){
        List<Department> childrenTreeNode = new ArrayList<Department>();
        for (Department dt : list) {
            if(null!=dt.get_parentId() && dt.get_parentId() == nodeId){
                childrenTreeNode.add(dt);
            }
        }
        return childrenTreeNode;
    }
    /**
     * 递归返回树形结构
     * @param rootId
     * @param list
     * @return
     */
    private DepartmentCondition generateTreeNode(int rootId,List<Department> list){
        Department department = this.getNodeById(rootId,list);
        DepartmentCondition dc=new DepartmentCondition();
        dc.setId(department.getId());
        dc.setText(department.getName());
        List<Department> childrenTreeNode = this.getChildrenNodeById(rootId,list);
        for (Department dt : childrenTreeNode) {
            DepartmentCondition node = this.generateTreeNode(dt.getId(),list);
            dc.getChildren().add(node);
        }
        return dc;
   }
}
