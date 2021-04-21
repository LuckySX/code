package com.flow.traffic.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: lfh
 * Version: 1.0
 * Date: 2017/1/3
 * Description: 字符串工具类
 * Function List:
 */
public class StringUtil {

    /**
     * 获取32位UUID字符串
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 不区分大小写比较字符串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    /**
     * 去空格，判断是否null 或者 ""
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断是否null 或者 ""
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    public static String isNullDefault(String str,String defaultStr) {
		if(!isNotNull(str))
			return defaultStr;
		return str;
	}
    

    /**
     * 去空格，当字符串为null或者""时，转换为指定的字符串
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    /**
     * 当字符串为null或者""时，转换为指定的字符串
     *
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * 检查是否全是英文小写。
     *
     * @param str
     * @return
     */
    public static boolean isAllLowerCase(String str) {
        if (str != null && !isEmpty(str)) {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isLowerCase(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否全是英文大写。
     *
     * @param str
     * @return
     */
    public static boolean isAllUpperCase(String str) {
        if (str != null && !isEmpty(str)) {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isUpperCase(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否只包含数值
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 检查是否只包含数值或者空格
     *
     * @param str
     * @return
     */
    public static boolean isNumericSpace(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != 32) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 检查是否只是空格或""
     *
     * @param str
     * @return
     */
    public static boolean isWhitespace(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 查询字符串中指定字符串出现的次数
     *
     * @param str
     * @param sub
     * @return
     */
    public static int countMatches(String str, String sub) {
        if (!isEmpty(str) && !isEmpty(sub)) {
            int count = 0;

            for (int idx = 0; (idx = str.indexOf(sub, idx)) != -1; idx += sub.length()) {
                ++count;
            }

            return count;
        } else {
            return 0;
        }
    }

    /**
     * 判断字符串是否不为空（不为"null","undefined"）
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        boolean b = false;
        if (str != null && str.trim().length() > 0 && (!str.equalsIgnoreCase("null")) && (!str.equals("undefined"))) {
            b = true;
        }
        return b;
    }

    /**
     * 判断Map是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotNull(Map<?, ?> map) {
        return !((map == null) || map.isEmpty());
    }

    /**
     * List集合是否为空
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isNotNull(List<T> list) {
        boolean b = false;
        if (null != list && list.size() > 0) {
            b = true;
        }
        return b;
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(StringBuffer str) {
        return (str != null) && (!"".equalsIgnoreCase(str.toString())) && (str.toString().trim().length() >= 1);
    }

    /**
     * 字符串数组是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String[] str) {
        return (str != null) && (str.length != 0);
    }

    /**
     * 对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        boolean b = false;
        if (null != obj) {
            b = true;
        }
        return b;
    }

    /**
     * 如果字符串为null,修正为""
     *
     * @param str
     * @return
     */
    public static String toString(String str) {
        return str != null ? str.trim() : "";
    }

    /**
     * BigDecimal转换为字符串
     *
     * @param str
     * @return
     */
    public static String toString(BigDecimal str) {
        return str != null ? str.toString() : "";
    }

    /**
     * 将ISO-8859转置为GBK
     *
     * @param str
     * @return
     */
    public static String formatStrGBK(String str) throws Exception {
        String result = "";
        if (isNotNull(str)) {
            result = new String(str.getBytes("ISO-8859-1"), "GBK");
        }
        return result.trim();
    }

    /**
     * 将ISO-8859转置为UTF8
     *
     * @param str
     * @return
     */
    public static String formatStrUTF(String str) throws Exception {
        String result = "";
        if (isNotNull(str)) {
            result = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        }
        return result.trim();
    }

    /**
     * 将ISO-8859转置为UTF8
     *
     * @param str
     * @return
     */
    public static String formatStrUTF(StringBuilder str) throws Exception {
        String result = "";
        if (isNotNull(str)) {
            result = new String(str.toString().getBytes("ISO-8859-1"), "UTF-8");
        }
        return result.trim();
    }

    /**
     * 将字符串格式化HTML字符串
     *
     * @param str
     * @return
     */
    public static String formatHTML(String str) {
        return str != null ? str.trim().replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;") : "";
    }

    /**
     * 匹配正则表达式的结果存储在List
     *
     * @param regex
     * @param str
     * @return
     */
    public static List<String> regex(String regex, String str) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 字符串替换
     *
     * @param strSource
     * @param strFrom
     * @param strTo
     * @return
     */
    public static String replaceStr(String strSource, String strFrom, String strTo) {
        if (strSource == null) {
            return null;
        }
        int i = 0;
        if ((i = strSource.indexOf(strFrom, i)) >= 0) {
            char[] cSrc = strSource.toCharArray();
            char[] cTo = strTo.toCharArray();
            int len = strFrom.length();
            StringBuffer buf = new StringBuffer(cSrc.length);
            buf.append(cSrc, 0, i).append(cTo);
            i += len;
            int j = i;
            while ((i = strSource.indexOf(strFrom, i)) > 0) {
                buf.append(cSrc, j, i - j).append(cTo);
                i += len;
                j = i;
            }
            buf.append(cSrc, j, cSrc.length - j);
            return buf.toString();
        }
        return strSource;
    }



    /**
     * 是否符合邮箱格式
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        return match(regex, str);
    }

    /**
     * 是否符合网址格式
     *
     * @param str
     * @return
     */
    public static boolean isHomepage(String str) {
        String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
        return match(regex, str);
    }

    /**
     * 符合 regex的正则表达式格式
     *
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public static String selectIspCode(String str) {
        String regex1 = "^(86|)1(34[0-8]|(3[5-9]|5[0127-9]|8[23478]|78|47)\\d)\\d{7}$";
        String regex2 = "^(86|)1(3[0-2]|5[256]|8[56]|7[156]|45)\\d{8}$";
        String regex3 = "^(86|)1((33|53|8[019]|7[37])[0-9]|349)\\d{7}$";
        if(str.matches(regex1))
            return "移动";
        if(str.matches(regex2))
            return "联通";
        if(str.matches(regex3))
            return "电信";
        return "未知";
    }
    
    public static void main(String[] args) {
        String a = "8615592518131";
        System.out.println(selectIspCode(a));
    }

}
