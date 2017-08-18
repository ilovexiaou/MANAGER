package com.utils.quartz.service;
@SuppressWarnings("unused")
public class TimeTransform {

    private static final String YEAR="0 0 1 1 1 ?";   //每年1月1号1点0分0秒
    
    private static final String MONTH="0 0 1 1 * ?";   //每月1号1点0分0秒
    
    private static final String WEEK="0 0 1 ? * MON";  //每周1凌晨1点执行
    
    private static final String DAY="0 0 1 * * ?";  ////每天凌晨1点执行
    
    private static final String MINUTES="0 0/1 * * * ?";  //每分钟0秒执行一次，测试用
    
    public String transform(String month,String day,String week){  
        //month 范围 1-12 即1-12月
        //day 范围1-31 即1-31日 注意特殊月份2月28天 最好1-28
        //week 范围MON-SUN
        String result="";
        if (week==null || week.equals("")) { //week 每周一次 ，他选其他都必须为null
            if (month==null || month.equals("")) { 
                if (day==null || day.equals("")) {
                    result=DAY;
                }else{
                result="0 0 1 "+day+" * ?";
                }
            }else {
                result="0 0 1 "+day+" "+month+" ?";
            }
        }else{
            result="0 0 1 ? * "+week; //每周一次
        }
        return result;
    }
}
