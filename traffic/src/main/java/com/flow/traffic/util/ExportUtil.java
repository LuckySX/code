package com.flow.traffic.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ExportUtil {

    private final static int max = 1000000;


    public static <T> int export(HttpServletResponse response, String fileName, String sheetName,
                                 List<T> list
            ,boolean isReturn) {

        OutputStream out = null;
        try {
            if (list==null || list.size()==0) {
                if(isReturn){
                    response.setContentType("application/text; charset=utf-8");
                    PrintWriter pw = response.getWriter();
                    pw.print(-1);
                }


                // if(isReturn)
                //ResponseWriteUtil.writeResponseResult(response, false, "查询列表为空，请重新查询后导出");
                return -1;
            }

            int type = list.get(0) instanceof Map?1:2;
            if(!fileName.endsWith(".xlsx")){
                fileName = fileName+".xlsx";
            }

            response.setHeader("Content-Disposition", "inline; filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            // HSSFWorkbook workBook = new HSSFWorkbook();
            XSSFWorkbook workBook = new XSSFWorkbook();

            out = response.getOutputStream();
            if(list.size()<=1048576) {
                ExportExcel.exportExcelEx(workBook, sheetName, (List<Map<String,Object>>) list,0);
            }else{
                int size = list.size();
                for(int i=0;i<(size+max-1)/max;i++){
                    ExportExcel.exportExcelEx(workBook, sheetName, (List<Map<String,Object>>)  list,max);

                }
            }
            workBook.write(out);
            // if(isReturn)
            //      ResponseWriteUtil.writeResponseResult(response, true, "导出成功");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out!=null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return -2;
    }

    /**
     * 导出xls
     * @param response
     * @param fileName 文件名称 数据导出-域名核验.xls
     * @param sheetName sheetName名称
     * @param headersChinese 表格第一行title
     * @param headersEnglish 解析数据字段
     * @param list 数据
     * @param isReturn 是否直接显示错误日志
     * @return
     * -1 查询列表为空，请重新查询后导出
     * -2 其他错误
     * 1 导出成功
     */
    public static <T> int export(HttpServletResponse response, String fileName, String sheetName,
                                 String[] headersChinese, String[] headersEnglish, List<T> list
            ,boolean isReturn) {

        OutputStream out = null;
        try {
            if (list==null || list.size()==0) {
                if(isReturn){
                    response.setContentType("application/text; charset=utf-8");
                    PrintWriter pw = response.getWriter();
                    pw.print(-1);
                }


                // if(isReturn)
                //ResponseWriteUtil.writeResponseResult(response, false, "查询列表为空，请重新查询后导出");
                return -1;
            }

            int type = list.get(0) instanceof Map?1:2;
            if(!fileName.endsWith(".xlsx")){
                fileName = fileName+".xlsx";
            }

            response.setHeader("Content-Disposition", "inline; filename="
                    + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            // HSSFWorkbook workBook = new HSSFWorkbook();
            XSSFWorkbook workBook = new XSSFWorkbook();

            out = response.getOutputStream();
            if(list.size()<=1048576) {
                if(type==1)
                    ExportExcel.exportExcelEx(workBook, sheetName, headersChinese, headersEnglish, (List<Map>) list,0);
                else
                    ExportExcel.exportExcel(workBook, sheetName, headersChinese, headersEnglish, list,0);
            }else{
                int size = list.size();
                for(int i=0;i<(size+max-1)/max;i++){
                    if(type==1)
                        ExportExcel.exportExcelEx(workBook, sheetName+(i+1), headersChinese, headersEnglish, (List<Map>) list,max);
                    else
                        ExportExcel.exportExcel(workBook, sheetName+(i+1), headersChinese, headersEnglish, list,max);
                }
            }
            workBook.write(out);
            // if(isReturn)
            //      ResponseWriteUtil.writeResponseResult(response, true, "导出成功");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out!=null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return -2;
    }


    public static<T> int export(HttpServletResponse response, String fileName, String tabName,
                                String[] headersChinese, String[] headersEnglish, List<T> list) {
        return export(response,fileName,tabName,headersChinese,headersEnglish,list,true);
    }

    public static<T> int export(HttpServletResponse response, String fileName,
                                String[] headersChinese, String[] headersEnglish, List<T> list) {
        return export(response,fileName,fileName,headersChinese,headersEnglish,list,true);
    }

    public static<T> int export(HttpServletResponse response, String fileName,
                                List<T> list) {
        return export(response,fileName,fileName,list,true);
    }

}
