package com.mg.api.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date current() {
		return new Date();
	}

	/**
	 * 判断datetime1是否晚于datetime2
	 * @param datetime1
	 * @param datetime2
	 * @return
	 */
	public static boolean isDateTimeAfter(Date datetime1, Date datetime2){
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(datetime1);
		calendar2.setTime(datetime2);
		
		return calendar1.after(calendar2);
	}
	
	/**
	 * 判断datetime1是否早于datetiem2
	 * @param datetime1
	 * @param datetime2
	 * @return
	 */
	public static boolean isDateTimeBefore(Date datetime1, Date datetime2){
		return isDateTimeAfter(datetime2,datetime1);
	}
	
	/**
	 * 获得指定日期之后一段时期的日期。例如某日期之后3天的日期等。
	 * 
	 * @param origDate
	 *            基准日期
	 * @param amount
	 *            时间数量
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 * @return
	 */
	public static final Date dateAfter(Date origDate, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(timeUnit, amount);
		return calendar.getTime();
	}

	/**
	 * 获得指定日期之前一段时期的日期。例如某日期之前3天的日期等。
	 * 
	 * @param origDate
	 *            基准日期
	 * @param amount
	 *            时间数量
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 * @return
	 */
	public static final Date dateBefore(Date origDate, int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origDate);
		calendar.add(timeUnit, -amount);
		return calendar.getTime();
	}
	
	/**
	 * 将字符串形式的日期表示解析为日期对象
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(
					dateString, new String[] { "yyyy-MM-dd", "yyyy-M-d",
							"yyyy-MM-d", "yyyy-M-dd", "yyyyMMdd" });
		} catch (ParseException e) {
			return null;
		}
	}
	
    /**
     * 以指定日期样式转换字符串对象为日期型对象
     * @param dateString
     * @param pattern
     * @return
     * @throws ParseException 
     */
    public static Date getDateByPattern(String dateString, String pattern) {
          Date date = null;
          
          try {
                SimpleDateFormat sdf=new SimpleDateFormat(pattern);
                date=sdf.parse(dateString);
          } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
          }
          return date;
    }
          
    /**
     * 以指定日期样式转换日期为字符串对象
     * @param date
     * @param pattern
     * @return
     */
    public static String getStringByPattern(Date date, String pattern){
          String dateString = null;
          
          SimpleDateFormat sdf=new SimpleDateFormat(pattern);
          dateString = sdf.format(date);
          
          return dateString;
    }
}
