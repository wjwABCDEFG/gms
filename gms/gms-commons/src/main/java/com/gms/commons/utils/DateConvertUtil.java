/**
 * @author wjw
 * @date 2020/4/22 3:51
 */
package com.gms.commons.utils;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConvertUtil {

    //日期转换成字符串
    public static String date2String(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    //字符串转换成日期
    public static Date string2Date(String str, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }

    //字符串转换成日期
    public static Date string2Date(String str) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat(patt);
//        Date parse = sdf.parse(str);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date parse = fmt.parse(str);
        return parse;
    }

    //字符串转换成星期
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    @Test
    public void test(){
        System.out.println(DateConvertUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
