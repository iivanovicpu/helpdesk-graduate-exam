package hr.veleri.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author mstipanov
 * @version 1.1
 * @since 2006.05.29 12:57:10
 */
public final class UtilitiesDate {
    public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final DateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat("HH:mm");

    private UtilitiesDate() {
    }

    public static boolean isToday(Date date) {
        return getDate(new Date()).compareTo(getDate(date)) == 0;
    }

    public static Date getDate(Date dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        return getDate(year, month, day, 0, 0);
    }

    public static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDate(String dateString) throws ParseException {
        return getDate(dateString, DEFAULT_DATE_FORMAT);
    }

    public static Date getDateTime(String dateString) throws ParseException {
        return getDate(dateString, DEFAULT_DATETIME_FORMAT);
    }

    public static Date getDate(String dateString, DateFormat dateFormat) throws ParseException {
        return dateFormat.parse(dateString);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    public static String format(Date date, DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date set(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, value);
        return calendar.getTime();
    }

    public static String getTimestamp(String pattern) {
        return format(new Date(), new SimpleDateFormat(pattern));
    }

    public static String getTimeStamp() {
        return format(new Date(), new SimpleDateFormat("yyyyMMdd-HHmmss"));
    }

    public static String formatTime(Date vrijeme) {
        return format(vrijeme, DEFAULT_TIME_FORMAT);
    }

    public static String formatDateTime(Date vrijeme) {
        return format(vrijeme, DEFAULT_DATETIME_FORMAT);
    }

    public static Date getToday() {
        return getDate(new Date());
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int get(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }
}
