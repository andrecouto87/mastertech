package br.com.couto.mastertech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getEndDate(Date data) {
        try {
            Calendar calendar = Calendar.getInstance();

            if (!isToday(data)) {
                return new SimpleDateFormat("HH:mm:ss").parse("23:59:59");
            } else {
                return new SimpleDateFormat("HH:mm:ss").parse(
                        calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                                calendar.get(Calendar.MINUTE) + ":" +
                                calendar.get(Calendar.SECOND)
                );
            }
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date getZeroTimeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDate(String date) {
        try {
            return new SimpleDateFormat("yyyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date getTime(String date) {
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Boolean isToday(Date date) {
        Date paramDate = getZeroTimeDate(date);
        Date actualDate = getZeroTimeDate(new Date());
        return paramDate.equals(actualDate);
    }

}
