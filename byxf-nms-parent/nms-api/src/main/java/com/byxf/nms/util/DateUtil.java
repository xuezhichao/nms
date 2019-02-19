package com.byxf.nms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

    public final static String DATE_FROMAT_YYYYMMDD = "yyyyMMdd";

    public final static String DATE_FROMAT_YYMMDD = "yyMMdd";

    public final static String TIME_FORMAT_HHMMSS = "HHmmss";

    /**
     * 月份格式化
     */
    public static final String YYYY_MM_FORMAT = "yyyy-MM";

    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";

    public static final String YYYY$MM$DD_FORMAT = "yyyy/MM/dd";

    public static final String YYYY$MM$DD$hh$mm$ss_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 时间精确到秒格式化
     */
    public static final String YYYY_MM_DD$hh$mm$ss_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间精确到毫秒格式化
     */
    public static final String YYYY_MM_DD$hh$mm$ss$SSS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时间精确到毫秒格式化
     */
    public static final String YYYY_MM_DD$hh$mm$ss_SSS_FORMAT = "dd-MMM-yyyy HH:mm:ss:SSS";

    public static final String hh$mm$ss_FORMAT = "HH:mm:ss";

    public static final String hhmmssSSS_FORMAT = "HHmmssSSS";

    public static final String YYYY_MM_DDT$hh$mm$ss_SSS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String DATE_FROMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 两个日期是否在跨度之内
     *
     * @param gapType 跨度类型，如Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_YEAR
     * @param maxGap 最大跨度值
     */
    public static boolean isWithInDateGap(Date startDate, Date endDate, int gapType, int maxGap) {
        if (startDate == null) {
            throw new IllegalArgumentException("The startDate must not be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("The endDate must not be null");
        }
        if (gapType != Calendar.YEAR && gapType != Calendar.MONTH && gapType != Calendar.DAY_OF_YEAR) {
            throw new IllegalArgumentException("The value of gapType is invalid");
        }

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        start.add(gapType, maxGap);
        int compare = start.getTime().compareTo(endDate);

        return compare >= 0;
    }


    public static String dataFormat(Date date , String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 两个日期是否在跨度之内
     *
     * @param gapType 跨度类型，如Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_YEAR
     * @param maxGap 最大跨度值
     */
    public static boolean isWithInDateGap(String startDate, String endDate, int gapType, int maxGap) {
        Date startDateTime = null;
        Date endDateTime = null;
        try {
            startDateTime = DateUtils.parseDate(startDate, DATE_FROMAT_YYYYMMDD);
            endDateTime = DateUtils.parseDate(endDate, DATE_FROMAT_YYYYMMDD);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误,开始日期：" + startDate + ",结束日期：" + endDate, e);
        }

        return isWithInDateGap(startDateTime, endDateTime, gapType, maxGap);
    }

    /**
     * 两个日期是否在跨度之内
     *
     * @param gapType 跨度类型，如Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_YEAR
     * @param maxGap 最大跨度值
     */
    public static boolean isWithInDateGap(int startDate, int endDate, int gapType, int maxGap) throws ParseException {
        return isWithInDateGap(DateUtils.parseDate(String.valueOf(startDate), DATE_FROMAT_YYYYMMDD),
                DateUtils.parseDate(String.valueOf(endDate), DATE_FROMAT_YYYYMMDD), gapType, maxGap);
    }

    /**
     * <b>说明:</b> 获取系统当前日期
     *
     * @
     * @since 2014年5月22日
     */
    public static int getCurIntDate() {
        return Integer.parseInt(getCurStringDate());
    }

    public static Date getCurrentTime(){
        return new Date();
    }

    /**
     * <b>说明:</b> 获取系统当前日期
     *
     * @
     * @since 2014年5月22日
     */
    public static String getCurStringDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatdate = new SimpleDateFormat(DATE_FROMAT_YYYYMMDD);
        return formatdate.format(currentDate);
    }

    /***
     * <b>说明:</b> 获取指定格式的系统当前日期
     *
     * @param
     * @return
     * @
     *
     * 	@since 2014年5月26日
     */
    public static String getCurDate(String strFormat) {
        Date currentDate = new Date();
        SimpleDateFormat formatdate = new SimpleDateFormat(strFormat);
        return formatdate.format(currentDate);
    }

    /***
     * <b>说明:</b> 获取当时系统日期时间【YYYYMMDDHHmmss】
     *
     * @param
     * @return
     *
     * @since 2014年6月5日
     */
    public static String getCurStringDateTime() {
        Date currentDate = new Date();
        SimpleDateFormat formatdate = new SimpleDateFormat(DATE_FROMAT_YYYYMMDD + TIME_FORMAT_HHMMSS);
        return formatdate.format(currentDate);
    }

    /**
     * <b>说明:</b> 获取当时系统日期时间【YYYYMMDDHHmmss】
     *
     * @since 2014年6月5日
     */
    public static Long getIntCurIntDateTime() {
        return Long.valueOf(getCurStringDateTime());
    }

    /**
     * <b>说明:</b> 获取系统当前时间
     *
     * @return 当前时间并格式化成“HHmmss”,如“123124”
     * @
     * @since 2014年5月22日
     */
    public static String getCurTime() {
        Date currentDate = new Date();
        SimpleDateFormat formatdate = new SimpleDateFormat(TIME_FORMAT_HHMMSS);
        return formatdate.format(currentDate);
    }

    /**
     * <b>说明: </b>验证传入数值型日期[YYYYMMDD]是否合法
     *
     * @
     * @since 2014年5月22日
     */
    public static boolean checkDateFormat(int intDate) {
        return checkDateFormat(String.valueOf(intDate));
    }

    /**
     * <b>说明: </b>验证传入字符型日期[YYYYMMDD]是否合法
     *
     * @ @since 2014年5月22日
     */
    public static boolean checkDateFormat(String strDate) {
        return checkDateFormat(strDate, DATE_FROMAT_YYYYMMDD);
    }

    /**
     * <b>说明: </b>验证传入字符型日期是否合法
     *
     * @ @since 2014年5月22日
     */
    public static boolean checkDateFormat(int intDate, String strFormat) {
        return checkDateFormat(String.valueOf(intDate), strFormat);
    }

    /**
     * <b>说明: </b>验证传入字符型日期是否合法
     *
     * @ @since 2014年5月22日
     */
    public static boolean checkDateFormat(String strDate, String strFormat) {
        try {
            DateUtils.parseDateStrictly(strDate, strFormat);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * <b>说明: </b>验证传入字符型时间[HH24MMSS]是否合法
     *
     * @ @since 2014年5月22日
     */
    public static boolean checkTimeFormat(String strDate) {
        return checkTimeFormat(strDate, TIME_FORMAT_HHMMSS);
    }

    /**
     * <b>说明: </b>验证传入字符型时间是否合法
     *
     * @ @since 2014年5月22日
     */
    public static boolean checkTimeFormat(int intDate, String strFormat) {
        return checkTimeFormat(String.valueOf(intDate), strFormat);
    }

    /**
     * <b>说明: </b>验证传入字符型时间是否合法
     *
     * @ @since 2014年5月22日
     */
    public static boolean checkTimeFormat(String strDate, String strFormat) {
        try {
            DateUtils.parseDateStrictly(strDate, strFormat);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * <b>说明: </b>日期转换
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, DATE_FROMAT_YYYYMMDD);
    }

    /**
     * <b>说明: </b>日期转换
     */
    public static Date parseDate(String strDate, String strFormat) {
        try {
            return DateUtils.parseDateStrictly(strDate, strFormat);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误,日期：" + strDate, e);
        }
    }

    /**
     * <b>说明: </b>日期转换
     */
    public static Date parseDate(int intDate, String strFormat) {
        return parseDate(String.valueOf(intDate), strFormat);
    }

    /**
     * <b>说明: </b>日期转换
     */
    public static Date parseDate(int intDate) {
        return parseDate(String.valueOf(intDate));
    }

    /**
     * 日期转换成字符串
     */
    public static String date2String(Date date, String dateFormat) {
        SimpleDateFormat formatdate = new SimpleDateFormat(dateFormat);
        return formatdate.format(date);
    }

    /**
     * 日期转换成字符串
     *
     * @return 格式为YYYYMMDD
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_FROMAT_YYYYMMDD);
    }

    public static String date2Stringhms(Date date) {
        return date2String(date, DATE_FROMAT_YYYYMMDDHHMMSS);
    }

    /**
     * 日期转换成整数
     *
     * @return 格式为YYYYMMDD
     */
    public static int date2Int(Date date) {
        String str = date2String(date, DATE_FROMAT_YYYYMMDD);
        return Integer.parseInt(str);
    }

    /**
     * 获取指定日期之前的相隔天数的日期
     *
     * @param inputDate 日期
     * @param days 天数
     * @since 2014年7月1日
     */
    public static Integer getDateBeforeDay(Integer inputDate, int days) {

        Calendar theCa = Calendar.getInstance();
        theCa.setTime(parseDate(inputDate));
        theCa.add(Calendar.DATE, -1 * days);
        Date date = theCa.getTime();
        SimpleDateFormat formatdate = new SimpleDateFormat(DATE_FROMAT_YYYYMMDD);
        return Integer.valueOf(formatdate.format(date));
    }

    /**
     * 获取指定日期之后的相隔n年的日期
     *
     * @return Integer
     */
    public static Integer getDateAfterYear(Integer inputDate, int years) {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(parseDate(inputDate));
        theCa.add(Calendar.YEAR, years);
        Date date = theCa.getTime();
        SimpleDateFormat formatdate = new SimpleDateFormat(DATE_FROMAT_YYYYMMDD);
        return Integer.valueOf(formatdate.format(date));
    }

    /**
     * 获取指定日期之后的相隔天数的日期
     *
     * @param date 日期
     * @param days 天数
     * @since 2014年7月1日
     */
    public static Integer getDateAfterDay(Integer date, int days) {

        Calendar theCa = Calendar.getInstance();
        theCa.setTime(parseDate(date));
        theCa.add(Calendar.DATE, 1 * days);
        Date tempdate = theCa.getTime();
        SimpleDateFormat formatdate = new SimpleDateFormat(DATE_FROMAT_YYYYMMDD);
        return Integer.valueOf(formatdate.format(tempdate));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        return daysBetween(smdate, bdate, YYYY_MM_DD_FORMAT);
    }

    /**
     * daysBetween:(计算两个日期之间相差的天数). <br/>
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @param format 时间格式
     * @author huangbing
     * @since JDK 1.7 Date:2017年1月6日下午12:55:06
     */
    public static int daysBetween(Date smdate, Date bdate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long millisecond = time2 - time1;
        long between_days = millisecond / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的毫秒数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差毫秒数
     */
    public static long millisecondBetween(Date smdate, Date bdate) throws ParseException {
        return millisecondBetween(smdate, bdate, YYYY_MM_DD$hh$mm$ss_SSS_FORMAT);
    }

    /**
     * 计算两个日期之间相差的毫秒数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @param format 时间格式
     * @author huangbing
     * @since JDK 1.7 Date:2017年1月6日下午12:53:13
     */
    public static long millisecondBetween(Date smdate, Date bdate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long millisecond = time2 - time1;
        return millisecond;
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        return daysBetween(smdate, bdate, YYYY_MM_DD_FORMAT);
    }

    /**
     * daysBetween:(字符串的日期格式的计算). <br/>
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @param format 时间格式
     * @author huangbing
     * @since JDK 1.7 Date:2017年1月6日下午12:53:52
     */
    public static int daysBetween(String smdate, String bdate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 当前时间之前的某个时间
     */
    public static Date beforeDate(long intervalsTime) {
        long time = System.currentTimeMillis();
        return new Date(time - intervalsTime);
    }

    /**
     * 当前时间之前的某个时间
     */
    public static Date beforeDateByMinute(int minute) {
        long time = System.currentTimeMillis();
        return new Date(time - minute * 1000 * 60);
    }

    /**
     * 当前时间之前的某个时间
     */
    public static Date beforeDateByHour(int Hour) {
        long time = System.currentTimeMillis();
        return new Date(time - Hour * 1000 * 60 * 60);
    }

    /**
     * 当前时间之前的某个时间
     */
    public static Date beforeDateByDay(int day) {
        long time = System.currentTimeMillis();
        return new Date(time - day * 1000 * 60 * 60 * 24);
    }

    /**
     * getDayCount:(获取当月天数). <br/>
     *
     * @author huangbing
     * @since JDK 1.7 Date:2017年1月6日下午4:26:59
     */
    public static int getDayCount(int year, int month) {
        int result = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            result = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            result = 30;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                result = 29;
            } else {
                result = 28;
            }
        }
        return result;
    }

    /**
     * isLeapYear:(是否为闰年(Leap Year)). <br/>
     * 公历规定：年份是整百数时，必须是400的倍数才是闰年；不是400的倍数的世纪年，即使是4的倍数也不是闰年。
     * 通常所说的：四年一闰，百年不闰，四百年再闰。 例如，2000年是闰年，2100年则是平年。
     *
     * @since JDK 1.7 Date:2017年1月6日下午3:45:17
     */
    public static boolean isLeapYear(int year) {
        boolean result = false;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            result = true;
        }

        return result;
    }

    /**
     * getCurrDate:(获取当前时间的日期). <br/>
     *
     * @return 格式为yyyyMMdd，int类型。
     * @author Lalala
     * @since JDK 1.7
     */
    public static int getCurrDate(Date date) {
        String result = null;
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat();
            formatter.applyPattern(DATE_FROMAT_YYYYMMDD);
            result = formatter.format(date);
        }
        return Integer.parseInt(result);
    }

    /**
     * getCurrDate:(获取当前时间的时间). <br/>
     *
     * @return 格式为hhmmss，int类型。
     * @author Lalala
     * @since JDK 1.7
     */
    public static int getCurrTime(Date date) {
        String result = null;
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat();
            formatter.applyPattern(TIME_FORMAT_HHMMSS);
            result = formatter.format(date);
        }
        return Integer.parseInt(result);
    }

    /**
     * getYes30Day:(获取当前时间前指定天数的时间). <br/>
     *
     * @param day 指定天数
     * @author yinzhichao
     * @since JDK 1.7
     */
    public static Date getAppointDay(int day) {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(Calendar.DATE, -day);
        Date date = theCa.getTime();
        return date;
    }

    /**
     * intToStringDate:(转换Int类型日期为指定格式). <br/>
     *
     * @param date int类型日期
     * @param forma 转换后日期格式：如："yyyy-MM-dd"（默认）
     * @return 转换后日期
     * @author yinzhichao
     * @since JDK 1.7
     */
    public static String intToStringDate(int date, String forma) {
        String createDate = Integer.toString(date);
        String format = "";
        SimpleDateFormat formatter1 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = formatter.parse(createDate);
            if (!StringUtils.isBlank(format)) {
                formatter1 = new SimpleDateFormat(forma);
            }
            formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            format = formatter1.format(parse);
        } catch (ParseException e) {
        }

        return format;
    }

    /**
     * intToStringDate:(转换String类型日期为指定格式). <br/>
     *
     * @param date String类型日期
     * @param forma 转换后日期格式：如："HH:mm:ss"（默认）
     * @return 转换后日期
     * @author yinzhichao
     * @since JDK 1.7
     */
    public static String toStringDate(String date, String forma) {
        String format = "";
        SimpleDateFormat formatter1 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        try {
            Date parse = formatter.parse(date);
            if (!StringUtils.isBlank(format)) {
                formatter1 = new SimpleDateFormat(forma);
            }
            formatter1 = new SimpleDateFormat("HH:mm:ss");
            format = formatter1.format(parse);
        } catch (ParseException e) {
        }

        return format;
    }

    /**
     * getThisTime:(获取申请时间样本格式). <br/>
     *
     * @param createDate 日期
     * @param createTaime 时间
     * @author yinzhichao
     * @since JDK 1.7
     */
    public static String getThisTime(int createDate, String createTaime) {
        if (StringUtils.isBlank(createTaime) || createDate == 0) {
            return "";
        }

        String intToStringDate = intToStringDate(createDate, null);
        String stringDate = toStringDate(createTaime, null);
        return intToStringDate + "T" + stringDate;
    }
}
