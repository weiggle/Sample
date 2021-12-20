package com.github.weiggle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author wei.li
 * @created on 2021/11/17
 * @desc desc
 */
public class DataUtils {

    public static String getTimeInterval(String time)  {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date1 = null;
        try {
            date1 = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date(System.currentTimeMillis());
        Calendar from  =  Calendar.getInstance();
        from.setTime(date1);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(date2);
        int fromYear = from.get(Calendar.YEAR);
        int fromDay = from.get(Calendar.DAY_OF_YEAR);
        int toYear = to.get(Calendar.YEAR);
        int toDay = to.get(Calendar.DAY_OF_YEAR);
        if (toYear == fromYear) {
            return (toDay - fromDay) + "天";
        } else {
            if (toDay > fromDay) {
                return (toYear - fromYear) + "年又" + (toDay - fromDay) + "天";
            } else if (toDay == fromDay) {
                return (toYear - fromYear) + "年";
            } else {
                return (toYear - fromYear - 1) + "年又" + (365 + toDay - fromDay) + "天";
            }
        }
    }
}
