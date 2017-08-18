package com.personnel.message.controller;

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

import com.personnel.message.bean.Message;
import com.personnel.message.service.MessageService;
import com.utils.json.JsonUtils;

@Controller
public class MessageController {
    @Resource
    MessageService messageService;
    
    @RequestMapping("message/getAll.do")
    public  String listAll(Model model,HttpServletRequest request){
        //调取时间计时器
        /*TaskManager tm =TaskManager.getInstance();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "1");
        map.put("taskId", "1");
        map.put("task_table", "personnel_message");
        try {
            tm.addJob("测试任务", null, "0/10 * * * * ?", map);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return "/jsp/personnel/message/showMessage";
    }
    /**
     * 分页显示所有员工
     * @param response
     * @param request
     */
    @RequestMapping(value = "message/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request) {
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Message> messageList;
        int count;
        Map<String, Object> map;
        messageList=messageService.getPageMessage(offset, limit);
        count=messageService.countAllMessage();
        
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", messageList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
