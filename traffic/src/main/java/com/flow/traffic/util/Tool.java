package com.flow.traffic.util;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
    //字符串（2016-05-21 11:11:1）转化为时间戳（12312312312）
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re_time;
    }
    public static boolean getResult(String starttime,String endtime){
        int result = (int) (Long.parseLong(endtime) - Long.parseLong(starttime));
        if(result>=86400){
            return true;
        }else{
            return false;
        }

    }
    //时间戳（12312）转化为时间格式（2016-05-21）
    public static String TimeStamp2DateNoYears(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat("MM-dd").format(new Date(timestamp));
        return date;
    }
    //时间戳（12312）转化为时间格式（2016-05-21 11:11:11）
    public static String TimeStamp2DateNoYear(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;

        String date = new SimpleDateFormat("MM-dd HH").format(new Date(timestamp));

//		  String date = new java.text.SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date(timestamp));

        return date;
    }
    //时间戳（12312）转化为时间格式（2016-05-21）
    public static String TimeStamp2Date(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp));
        return date;
    }
    //时间戳（12312）转化为时间格式（2016-05-21）
    public static String TimeStamp2DateAll(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }
    //时间戳（12312）转化为时间格式（2016-05-21 11:11:11）
    public static String TimeStamp2DateHour(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH").format(new Date(timestamp));
        return date;
    }

    public static String TimeStampEnd(String date){
        String hourDate = date.substring(6, 8);
        String dayDate = date.substring(3, 5);
        if("01".equals(dayDate)){
            String mouseDate = date.substring(0, 2);
            switch(mouseDate){
                case "01":   return hourDate = dayDate+".Jan";
                case "02":   return hourDate = dayDate+".Feb";
                case "03":   return hourDate = dayDate+".Mar";
                case "04":   return hourDate = dayDate+".Apr";
                case "05":   return hourDate = dayDate+".May";
                case "06":   return hourDate = dayDate+".Jun";
                case "07":   return hourDate = dayDate+".Jul";
                case "08":   return hourDate = dayDate+".Aug";
                case "09":   return hourDate = dayDate+".Sep";
                case "10":   return hourDate = dayDate+".Oct";
                case "11":   return hourDate = dayDate+".Nov";
                case "12":   return hourDate = dayDate+".Dec";
            }
        }
        return dayDate+"_"+hourDate+":00";
    }
    public static String UnitConversion(String num){
        double i = Double.parseDouble(num);
        DecimalFormat df=new DecimalFormat("#.##");

        if(i<=1024&&i>=1){
            num = df.format(i)+"G";
        }
        if(i>1024){
            double j =i/1024;
            num = df.format(j)+"T";
        }
        if(i<1&&i>0.01){
            double j =i*1024;
            num =  df.format(j)	+"M";
        }
        if(i<0.01){
            double j =i*1024*1024;
            num =  df.format(j)	+"K";
        }
        return num;

    }
    public static String UnitConversionDanwei(String num,long time){
        //System.out.println("流量:"+num);
        //System.out.println("time:"+time);
        double i = Double.parseDouble(num)*8/time;
        //System.out.println("i:"+i);
        DecimalFormat df=new DecimalFormat("#.##");
        if(i>1024*1024*1024){
            double j =(i/(1024*1024*1024));
            num = df.format(j)+"G";
        }
        if(i<1024*1024*1024&&i>1024*1024){
            double j =(i/(1024*1024));
            num = df.format(j)+"M";
            //	return num;
        }
        if(i<1024*1024&&i>1024){
            double j =(i/1024);
            num =  df.format(j)	+"K";
            //	return num;
        }
        if(i<1024){
            double j =i;
            df=new DecimalFormat("#");
            num =  df.format(j);
            //		return num;
        }
        //	System.out.println("num:"+num);
        return num;
    }
    public static void main(String[] args) {
        System.out.println(TimeStampEnd("01-23:01"));
    }
////	  {field:'area',title:'区域',width:"15%",align:'left',halign:'left',sortable:false,},
//    {field:'webnum',title:'活跃网站数',width:"15%",align:'left',halign:'left',sortable:true},
//    {field:'webup',title:'网站上行流量',width:"15%",align:'left',halign:'left',sortable:true},
//    {field:'webdown',title:'网站下行流量',width:"15%",align:'left',halign:'left',sortable:true},
//    {field:'webupBps',title:'网站上行bps',width:"15%",align:'left',halign:'left',sortable:true},
//    {field:'webdownBps',title:'网站下行bps',width:"15%",align:'left',

    public static String getsort(String oldsort){
        String sort = "area";
        switch(oldsort){
            case "webdownBps": return sort="web_downbytes";
            case "webnum": return sort="webs";
            case "webup": return sort="web_upbytes";
            case "webdown": return sort="web_downbytes";
            case "webupBps": return sort="web_upbytes";
            case "area": return sort="area";
            case "usernum": return sort="ips";
            case "hostlink": return sort="ip_links";
            case "hostup": return sort="ip_upbytes";
            case "hostdown": return sort="ip_downbytes";
            case "hostupBps": return sort="ip_upbytes";
            case "hostdownBps": return sort="ip_downbytes";
        }

        return sort;

    }
}
