package com.utils.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

public class JsonUtils {
    /**
     * 数组对象转化为json输出到页面
     * @param obj
     * @param response
     * @throws IOException
     */
    public static void convertListToJson(Object[] obj,HttpServletResponse response) throws IOException{
        JSONArray json = JSONArray.fromObject(obj);  
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8"); 
        PrintWriter writer;
        writer = response.getWriter();
        writer.write(json.toString());
        writer.close();
    }
    /**
     * list转化为json输出到页面
     * @param list
     * @param response
     * @throws Exception
     */
    public static void convertListToJson(List<?> list,HttpServletResponse response)throws Exception{
        JSONArray json = JSONArray.fromObject(list);  
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8"); 
        PrintWriter writer;
        writer = response.getWriter();
        writer.write(json.toString());
        writer.close();
    }
    /**
     * map转化为json输出到页面
     * @param map
     * @param response
     * @throws Exception
     */
    public static void convertListToJson(Map<?,?> map,HttpServletResponse response)throws Exception{
        //防止把null转化为0的设置
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerDefaultValueProcessor(Integer.class,  
                new DefaultValueProcessor() {  
                    @Override
                    public Object getDefaultValue(@SuppressWarnings("rawtypes") Class type) {  
                        return null;  
                    }  
                });
        JSONObject json = JSONObject.fromObject(map, jsonConfig);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8"); 
        PrintWriter writer;
        writer = response.getWriter();
        writer.write(json.toString());
        writer.close();
    }
    /**
     * 对象转化为json输出到页面
     * @param obj
     * @param response
     * @throws IOException
     */
    public static void convertListToJson(Object obj,HttpServletResponse response) throws IOException{
        JSONObject json = JSONObject.fromObject(obj);  
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8"); 
        PrintWriter writer;
        writer = response.getWriter();
        writer.write(json.toString());
        writer.close();
    }
}
