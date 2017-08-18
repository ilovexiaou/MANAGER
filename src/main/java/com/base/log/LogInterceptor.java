package com.base.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class LogInterceptor {
    private static Logger logger = Logger.getLogger(LogInterceptor.class);
    @Before(value = "execution(* com.personnel.message.controller.*.*(..))")  
    public void before(){  
        logger.info("login start666!");  
    }  
    @After(value = "execution(* com.personnel.message.controller.*.*(..))")  
    public void after(){  
        logger.info("login end777!");  
    } 
    
}
