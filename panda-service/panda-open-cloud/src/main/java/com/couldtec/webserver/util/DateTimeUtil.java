package com.couldtec.webserver.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 实现对时间相关格式转换
 * 
 * @author qixq
 * @version 1.00, 11/19/2009
 * @since 1.0
 */
public class DateTimeUtil
{
	//服务器的时间
	private Calendar calendar = null;
	public static String yyyyMM = "yyyyMM";
	public static String yyyy_MM = "yyyy-MM";
	public static String yyyy_MM_dd = "yyyy-MM-dd";
	public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static String yyyyMMdd = "yyyyMMdd";
	public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static long genIdByTime(int n)
	{
		long id=(long) (System.currentTimeMillis()*Math.pow(10,n)+getRandom(n));
	    return id;
	}
	
	public static long getRandom(int n){
        if(n<1){  
            throw new IllegalArgumentException("随机数位数必须大于0");
        }  
        return (long)(Math.random()*9*Math.pow(10,n-1)) + (long)Math.pow(10,n-1);  
	}
	/**
	 * 构造函数，初始化时间发生器（无参数当前时间）
	 */
	public DateTimeUtil()
	{
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
	}
	/**
	 * 构造函数初始化时间发生器（带参数: 长整型时间数据毫秒）
	 */
	public DateTimeUtil(long serverDateTime)
	{
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar.setTimeInMillis(serverDateTime); // 指定时间
	}
	/**
	 * 构造函数初始化时间发生器（带参数: 长整型时间数据）
	 */
	public DateTimeUtil(Date DateTime)
	{
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar.setTimeInMillis(DateTime.getTime()); // 指定时间
	}
	/**
	 * 构造函数初始化时间发生器（带参数:Calendar类实例）
	 */
	public DateTimeUtil(Calendar cal)
	{
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar = cal;
	}
	/**
	 * 构造函数初始化时间发生器:DateTime参数格式yyyy-MM-dd HH:mm:ss or yyyy-MM-dd
	 */
	public DateTimeUtil(String DateTime)
	{
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		SimpleDateFormat s = null;
		Date date = null;
		try
		{
			if (DateTime.length() > 12)
			{
				s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			else
			{
				s = new SimpleDateFormat("yyyy-MM-dd");
			}
			date = new Date();
			try
			{
				date = s.parse(DateTime);
			}
			catch (ParseException e)
			{
				System.out.println("e:" + e.getMessage());
			}
		}
		catch (Exception ee)
		{
			System.out.println("ee:" + ee.getMessage());
		}
		calendar.setTime(date);
	}
	/**
	 * 构造函数初始化时间发生器:DateTime参数格式yyyy-MM-dd HH:mm:ss or yyyy-MM-dd
	 */
	public DateTimeUtil(String DateTime, String DateFormat)
	{
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		SimpleDateFormat s = null;
		Date date = null;
		try
		{
			s = new SimpleDateFormat(DateFormat);
			date = new Date();
			try
			{
				date = s.parse(DateTime);
			}
			catch (ParseException e)
			{
				System.out.println("e:" + e.getMessage());
			}
		}
		catch (Exception ee)
		{
			System.out.println("ee:" + ee.getMessage());
		}
		calendar.setTime(date);
	}
	
	/**
	 * 设置年月日等
	 * @param field
	 * @param value
	 */
	public void set(int field,int value){
		this.calendar.set(field, value);
	}
	
	/**
	 * 设置年月日
	 * @param year
	 * @param month
	 * @param date
	 */
	public void set(int year,int month,int date){
		this.calendar.set(year, month, date);
	}
	
	/**
	 * 设置年月日时分秒
	 * @param year
	 * @param month
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 */
	public void set(int year,int month,int date,int hourOfDay,int minute,int second){
		this.calendar.set(year, month, date, hourOfDay, minute, second);
	}
	
	/**
	 * 增加时间单位
	 * @param field
	 * @param amount
	 */
	public void add(int field,int amount){
		this.calendar.add(field, amount);
	}
	
	/**
	 * 获取当前年 yyyy
	 * 
	 * @return int
	 */
	public int getYear()
	{
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 获取当前月 mm
	 * 
	 * @return int
	 */
	public int getMonth()
	{
		return calendar.get(Calendar.MONTH) + 1;
	}
	/**
	 * 获取当前日dd
	 * 
	 * @return int
	 */
	public int getDay()
	{
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 当前当前小时hh
	 * 
	 * @return int
	 */
	public int getHour()
	{
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 获取当前分钟 mm
	 * 
	 * @return int
	 */
	public int getMinute()
	{
		return calendar.get(Calendar.MINUTE);
	}
	/**
	 * 获取当前秒ss
	 * 
	 * @return int
	 */
	public int getSecond()
	{
		return calendar.get(Calendar.SECOND);
	}
	/**
	 *	取从1970年1月1日以来的时间（毫秒）
	 * 
	 * @return long
	 */
	public long getTime()
	{
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 取时间型日期
	 * 
	 * @return Date
	 */
	public Date getDateTime(){
		return calendar.getTime();
	}
	
	/**
	 * 取从1970年1月1日以来的时间（秒）
	 * 
	 * @return long
	 */
	public long getLongTime()
	{
		return calendar.getTimeInMillis() / 1000;
	}
	/**
	 * 取从1970年1月1日以来,当前时间后一天的时间（秒）
	 * 
	 * @return long
	 */
	public long getNextLongTime()
	{
		Calendar calendar_temp = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar_temp.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
		calendar_temp.add(Calendar.DAY_OF_MONTH, 1);
		return calendar_temp.getTimeInMillis() / 1000;
	}
	/**
	 * 取从当前日期的偏移日期:年份
	 * 
	 * @param years
	 * @return int
	 */
	public int getNextYear(int years)
	{
		calendar.add(Calendar.YEAR, years);
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 取从当前日期的偏移日期:月份
	 * 
	 * @param months
	 * @return int
	 */
	public int getNextMonth(int months)
	{
		calendar.add(Calendar.MONTH, months);
		return calendar.get(Calendar.MONTH) + 1;
	}
	/**
	 * 取从当前日期的偏移日期:日期
	 * 
	 * @param dates
	 * @return int Calendar.DAY_OF_MONTH
	 */
	public int getNextDate(int dates)
	{
		calendar.add(Calendar.DATE, dates);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取YYYY-MM格式的当前时间
	 * 
	 * @return
	 */
	public String getYYYY_MM(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取YYYY-MM-DD格式的当前时间
	 * 
	 * @return
	 */
	public String getYYYY_MM_DD(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取yyyy-MM-dd HH:mm:ss格式的当前时间
	 * 
	 * @return
	 */
	public String getYYYY_MM_DD_HH_mm_ss(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取YYYYMM格式的当前时间
	 * 
	 * @return
	 */
	public String getYYYYMM(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMM);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取YYYYMMDD格式的当前时间
	 * 
	 * @return
	 */
	public String getYYYYMMDD(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取YYYYMMDDHHMMSS格式的当前时间
	 * 
	 * @return
	 */
	public String getYYYYMMDDHHMMSS(){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取YYYYWW 格式的当前时间
	 * 
	 * @return YYYYWW 格式的当前时间
	 */
	public String getYYYYWW(){
		int yyyy = this.getYear();
		int ww = this.getWeekOfYear();
		StringBuffer bf = new StringBuffer();
		bf.append(yyyy);
		if(10>ww){
			bf.append("0");
			bf.append(ww);
		}else{
			bf.append(ww);
		}
		return bf.toString();
	}
	/**
	 * 获取指定日期所在月的第一天的日期
	 * 
	 * @return
	 */
	public String getFirtDayOfMonth()
	{
		calendar.set(this.getYear(), this.getMonth() - 1, 1);
		return this.getYYYY_MM_DD();
	}
	
	/**
	 * 获取指定日期上个月的第一天的日期
	 * yyyy-mm-dd
	 * 
	 * @return
	 */
	public String getFirtDayOfLastMonth()
	{
		calendar.set(this.getYear(), this.getMonth() - 2, 1);
		return this.getYYYY_MM_DD();
	}
	
	/**
	 * 获得指定日期所在月的最后一天的日期
	 * 
	 * @return String
	 */
	public String getLastDayOfMonth()
	{
		// 移动到下一个月的第一天然后再后退一天
		calendar.set(this.getYear(), this.getMonth(), 1);
		this.getNextDate(-1);
		return this.getYYYY_MM_DD();
	}
	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return int
	 */
	public static int nDaysBetweenTwoDate(Date firstDate, Date secondDate)
	{
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}
	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstString
	 * @param secondString
	 * @return int
	 */
	public static int nDaysBetweenTwoDate(String firstString, String secondString)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		try
		{
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		}
		catch (Exception e)
		{
			// 日期型字符串格式错误
		}
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}
	/**
	 * 返回特定日期处于一年中的第几周 周的定义星期日到星期六
	 * 
	 * @return
	 */
	public int getWeekOfYear()
	{
		int weeks = calendar.get(Calendar.WEEK_OF_YEAR);
		// modify by xiaoxf 2008-1-1 求2007-12-31 会算成08年的第一周 故加以下循环
		while (weeks < 3 && this.getMonth() == 12)
		{
			this.getNextDate(-1);
			weeks = calendar.get(Calendar.WEEK_OF_YEAR) + 1;
		}
		return weeks;
	}
	/**
	 * 返回特定日期处于一周中的第几天<br>
	 * 中国周一为一周第一天CN<br>
	 * 国外周日为一周第一天US
	 * 
	 * @return int
	 */
	public int getDayOfWeek(String country)
	{
		return calendar.get(Calendar.DAY_OF_WEEK)
				- Integer.parseInt(country.equals("CN") ? "1" : "0");
	}
	/**
	 * 返回当前为一年中的第几天
	 */
	public int getDayOfYear()
	{
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	/**
	 * 东方是周一为一周的第一天CN<br>
	 * 西方是周日为一周的第一天US<br>
	 * 返回特定日期所处这一周的周一所处的日期
	 * 
	 * @return String
	 */
	public String getFirstDayOfWeek(String country)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// 西方是周日为一周的第一天
		// 东方是周一为一周的第一天
		Date dateBegin = new Date();
		dateBegin.setTime(calendar.getTimeInMillis()
				- (long) (getDayOfWeek(country) - 1) * 24 * 60 * 60 * 1000);
		return formatter.format(dateBegin);
	}
	/**
	 *  东方是周一为一周的第一天CN<br>
	 * 西方是周日为一周的第一天US<br>
	 * 返回特定日期所处这一周的周末所处的日期
	 * 
	 * @return String
	 */
	public String getLastDayOfWeek(String country)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// 西方是周日为一周的第一天
		// 东方是周一为一周的第一天
		Date dateEnd = new Date();
		dateEnd.setTime(calendar.getTimeInMillis() + (long) (7 - getDayOfWeek(country))
				* 24 * 60 * 60 * 1000);
		return formatter.format(dateEnd);
	}
	/**
	 * 解析参数（String）为日时分秒
	 * @param time （String）
	 * @return
	 */
	public static String parseDateStr(String time){
		long all = Long.parseLong(time);
		long day=0,hour=0,min=0,sec=0;
		day = all/(24*3600);
		sec = all - day*(24*3600);
		hour = sec/3600;
		sec = sec - hour*3600;
		min = sec/60;
		sec = sec - min*60;
		return day+"天"+hour+"小时"+min+"分"+sec+"秒";
	}
	/**
	 * 解析参数（long）为日时分秒
	 * @param time long
	 * @return
	 */
	public static String parseDateStr(long time){
		long day=0,hour=0,min=0,sec=0;
		day = time/(24*3600);
		sec = time - day*(24*3600);
		hour = sec/3600;
		sec = sec - hour*3600;
		min = sec/60;
		sec = sec - min*60;
		return day+"天"+hour+"小时"+min+"分"+sec+"秒";
	}
	public int getNextHour(int dates)
	{
		calendar.add(Calendar.HOUR, dates);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public String getYYYYmmddhhmmss(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYmmddhhmmss");
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取当前时间之后的时间
	 * @param dateType
	 * @param timeNumber
	 * @return
	 */
	public String getNextDateTime(String dateType, int timeNumber) {
		if (dateType.equals("year")) {
			this.calendar.add(1, timeNumber);
		} else if (dateType.equals("month")) {
			this.calendar.add(2, timeNumber);
		} else if (dateType.equals("day")) {
			this.calendar.add(5, timeNumber);
		} else if (dateType.equals("hour")) {
			this.calendar.add(10, timeNumber);
		} else if (dateType.equals("minute")) {
			this.calendar.add(12, timeNumber);
		} else if (dateType.equals("second")) {
			this.calendar.add(13, timeNumber);
		}
		return getYYYY_MM_DD_HH_mm_ss();
	}
	
	public static void main(String[] args)
	{
		/*DateTimeUtil timeUtil = new DateTimeUtil();
		System.out.println(timeUtil.getYYYYWW());
		System.out.println(timeUtil.getNextHour(1));
		System.out.println(timeUtil.getYYYY_MM());
		*/
		System.out.println(System.currentTimeMillis());
		System.out.print(genIdByTime(5));
	}
}