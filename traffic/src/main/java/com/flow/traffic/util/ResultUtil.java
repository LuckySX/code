package com.flow.traffic.util;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    public enum State {
        SUCCESS,FAIL,UNUSUAL,LOGIC,PARAMETER
    }

    public static Map initResult(Object data,State code){
        String [] codes = codeName(code).split("_");
        return initResult(data,Integer.parseInt(codes[0]),codes[1]);
    }

    public static Map initResult(Object data,int code,String msg){
        Map map = new HashMap<String,Object>();
        map.put("code",code);
        map.put("data",data);
        map.put("message",msg);
        return map;
    }

    public static Map success(Object data){
        return initResult(data, State.SUCCESS);
    }

    private  static String codeName(State code){
        switch (code){
            case SUCCESS: return "200_请求成功";
            case FAIL: return "1111_请求失败";
            case UNUSUAL: return "1000_系统异常";
            case LOGIC: return "2001_业务逻辑错误";
            case PARAMETER: return "2002_业务参数错误";
        }
        return "9999_未知异常";
    }
}
