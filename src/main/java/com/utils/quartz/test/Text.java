package com.utils.quartz.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.utils.quartz.service.TaskManager;
@Test
public class Text {
    public static void main(String[] args) throws Exception{
        TaskManager tm =TaskManager.getInstance();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 123);
        map.put("taskId", 123);
        map.put("task_table", "personnel_message");
        try {
            tm.addJob("我的任务","Taske", "0/10 * * * * ?", map);
            System.out.println("成啦");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("拜啦");
            e.printStackTrace();
        }
    }
}
