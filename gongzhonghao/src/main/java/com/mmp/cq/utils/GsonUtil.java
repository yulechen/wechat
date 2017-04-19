/**
 * @FILE:GsonUtil.java
 * @AUTHOR:chenqiu
 * @DATE:2016年8月8日 上午9:50:24
 **/
package com.mmp.cq.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*******************************************
 * @COMPANY:北京汇通天下科技有限公司
 * @CLASS:GsonUtil
 * @DESCRIPTION:	
 * @AUTHOR:chenqiu
 * @VERSION:v1.0
 * @DATE:2016年8月8日 上午9:50:24
 *******************************************/
public class GsonUtil {
    
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
    
    public static String toJson(Object obj){
       return gson.toJson(obj);
    }
    
    public static <T>  T toObject(String json,Class<T> clazz){
        return (T)gson.fromJson(json, clazz);
    }
    
}

