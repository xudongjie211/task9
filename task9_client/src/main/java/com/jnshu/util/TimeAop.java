package com.jnshu.util;



import java.util.logging.Logger;
 public  class  TimeAop{

    private Logger logger = Logger.getLogger("TimeAop.class");
     Long a;
     public Long before(){
         a =System.currentTimeMillis();
         return a;
     }
     public void after(){
         Long b =System.currentTimeMillis();
         Long c=b-a;
         logger.info("执行方法耗时："+c);
     }
 }