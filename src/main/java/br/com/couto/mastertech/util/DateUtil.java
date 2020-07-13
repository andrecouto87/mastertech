package br.com.couto.mastertech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SIMPLE_DATE_FORMAT_DD = "dd";
    public static final String SIMPLE_DATE_FORMAT_MM = "MM";
    public static final String SIMPLE_DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String SIMPLE_DATE_FORMAT_DD_MM_YY = "dd/MM/yy";
    public static final String SIMPLE_DATE_FORMAT_OUTPUT = "dd.MM.yyyy";
    public static final String SIMPLE_DATE_FORMAT_SLASH_OUTPUT = "dd/MM/yyyy";
    public static final String SIMPLE_DATE_FORMAT_MINI_OUTPUT = "dd/MM";
    public static final String SIMPLE_DATE_FORMAT_MMM_YY = "MMM/yy";
    public static final String SIMPLE_DATE_FORMAT_MMMM_YY = "MMMM/yy";
    public static final String SIMPLE_DATE_FORMAT_MMMM_YYYY = "MMMM yyyy";
    public static final String SIMPLE_DATE_FORMAT_DD_MMM_YYYY = "dd MMM. yyyy";
    public static final String SIMPLE_DATE_FORMAT_DD_M_EEEE = "dd 'de' MMMM, EEEE";
    public static final String SIMPLE_DATE_FORMAT_KK_MM = "KK:mm";
    public static final String SIMPLE_DATE_FORMAT_HH_MM = "hh:mm";
    public static final String SIMPLE_DATA_FORMAT_HH_MM_SS = "hh:mm:ss";
    public static final String SIMPLE_DATE_FORMAT_MM_SS = "mm:ss";
    public static final String SIMPLE_DATE_FORMAT_KK_MM_SS = "yyyy/MM/dd kk:mm:ss";
    public static final String SIMPLE_DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd hh:mm:ss.SSS";
    public static final String SIMPLE_DATA_FORMAT_HH_MM_SS_24H = "HH:mm:ss";
    public static final Locale BR_LOCALE = new Locale("pt", "BR");
    public static final String SIMPLE_DATE_FORMAT_SLASH_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd hh:mm:ss";
    public static final String SIMPLE_DATE_FORMAT_ISO_8601_ITAU = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String SIMPLE_DATE_FORMAT_UTC_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String SIMPLE_DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'UTC'";
    public static final String SIMPLE_DATE_FORMAT_DOT_DD_MM_YYYY = "dd.MM.yyyy";
    public static final String SIMPLE_DATA_FORMAT_ACCOUNT_ONLINE = "dd-MM-yyyy";
    public static final String MONTH_NAME_FORMAT = "LLLL";
    public static final String DATE_TIME_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DAY_OF_MONTH = "d 'de' MMMM";
    public static final String DAY_3LETTER_MONTH = "dd MMM";
    public static final String DAY_OF_3LETTER_MONTH = "dd 'de' MMM";
    public static final String THREE_LETTER_MONTH = "MMM";
    public static final String MONTH_OF_YEAR = "MMMM 'de' yyyy";
    public static final String DAY_OF_MONTH_OF_YEAR = "d 'de' MMMM 'de' yyyy";
    public static final String DAY = "dd";
    public static final String SIMPLE_DATE_FORMAT_YYYY_MM = "yyyy-MM";
    public static final String SIMPLE_DATE_FORMAT_MM_YYYY = "MM/yyyy";
    public static final int DAYS_DIFF = 1;
    public static final int MONTH_DIFF = 2;
    public static final String FIRST_DAY = "1";
    public static final String SIMPLE_DATA_FORMAT_YYYYMM = "yyyyMM";   

    private DateUtil() {}

    public static Date formatDate(final String date) {
        return formatDate(SIMPLE_DATE_FORMAT, date);
    }

    public static Calendar formatCalendar(final String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatDate(SIMPLE_DATE_FORMAT, date));
        return calendar;
    }

    public static Date formatCompentenceDate(final String date) {
        return formatDate(SIMPLE_DATA_FORMAT_YYYYMM, date);
    }

    public static String formatStringDate(final String formatType, final String date) {
        Date parsedDate = formatDate(date);

        if (parsedDate == null || formatType == null) {
            return null;
        }

        return new SimpleDateFormat(formatType, BR_LOCALE).format(parsedDate);
    }

    public static String formatDate(final Calendar date) {
        return formatDate(date.getTime());
    }

    public static String formatDate(final Date date) {
        return formatDate(SIMPLE_DATE_FORMAT_DD_MM_YYYY, date);
    }

    public static String formatDate(final String formatType, final Date date) {
        if (date == null || formatType == null) {
            return null;
        }

        final SimpleDateFormat dateFormatterInstance = new SimpleDateFormat(formatType, BR_LOCALE);
        return dateFormatterInstance.format(date);
    }

    public static String getFormattedStringDate(String date, String dateFormat) {
        Date newDate = formatDate(date);
        return formatDate(dateFormat, newDate);
    }

    public static Date formatDate(final String formatType, final String date) {
        try {
            final SimpleDateFormat dateFormatterInstance =
                    new SimpleDateFormat(formatType, BR_LOCALE);
            dateFormatterInstance.setLenient(false);
            return dateFormatterInstance.parse(date);
        } catch (ParseException e) {           
            return null;
        }
    }
    
    public static String formatTime(Date date) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATA_FORMAT_HH_MM_SS_24H);
    	return sdf.format(date);
    	
    }
    
    public static Date parseTime(String time) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATA_FORMAT_HH_MM_SS_24H);
    	try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
}