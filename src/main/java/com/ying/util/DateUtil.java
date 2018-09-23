package com.ying.util;

import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Chloe on 2018/9/22.
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static String addDay(String strDate, int addDays) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(strDate));
            cd.add(Calendar.DATE, addDays);
            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    public static String getNowDate() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(dt);
    }


    public static boolean isDefaultDateFormat(String strDate) {
        return true;
    }

}
