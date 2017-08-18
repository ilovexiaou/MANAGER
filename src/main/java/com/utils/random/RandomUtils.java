package com.utils.random;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtils {
    
    public static String getRamdom(){
        Random random = new Random();   
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss"); 
        Date date = new Date();
        return sdf.format(date)+random.nextInt(100);
    }
}
