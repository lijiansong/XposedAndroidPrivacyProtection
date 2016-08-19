package com.whu.log;

import java.io.Serializable;
import java.util.HashMap;

public class Log_MY implements Serializable{
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String content;
	private HashMap<String, String> log;
	public Log_MY(int year,int month,int day,int hour,int minute,String pkname,String content,int type)
	{
		
		this.log=new HashMap<String, String>();
		log.put("year",""+ year);
		log.put("month", ""+month);
		log.put("day", ""+day);
		log.put("hour", ""+hour);
		log.put("minute", ""+minute);
		log.put("content", content);
		log.put("type", ""+type);
		log.put("pkname", pkname);
		
	}
	public Log_MY(String year,String month,String day,String hour,String minute,String pkname,String content,int type)
	{
		
		this.log=new HashMap<String, String>();
		log.put("year",""+ year);
		log.put("month", ""+month);
		log.put("day", ""+day);
		log.put("hour", ""+hour);
		log.put("minute", ""+minute);
		log.put("content", content);
		log.put("type", ""+type);
		log.put("pkname", pkname);
		
	}
	
	public String getPkname()
	{
		return this.log.get("pkname");
	}
	public int getYear() {
		return Integer.parseInt(this.log.get("year"));
	}
	
	public int getMonth() {
		return Integer.parseInt(this.log.get("month"));
	}
	
	public int getDay() {
		return Integer.parseInt(this.log.get("day"));
	}
	
	public int getHour() {
		return Integer.parseInt(this.log.get("hour"));
	}
	
	public int getMinute() {
		return Integer.parseInt(this.log.get("minute"));
	}
	
	public String getContent() {
		return this.log.get("content");
	}
	public boolean getType()
	{
		return Boolean.parseBoolean(this.log.get("type"));
	}
	

}
