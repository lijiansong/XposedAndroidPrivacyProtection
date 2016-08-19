package com.whu.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.whu.log.Log_MY;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class LogDatabaseManager {

	private SQLiteDatabase logSQLiteDatabase = null;
	private LogDatabaseHelper logDatabaseHelper = null;
	private static final String tableName = "log";
	private static final int indicator = -1;

	private static final int ALL_Log = 0;
	private static final int OP = 1;
	private static final int WATCH = 2;
	
	private static LogDatabaseManager logDatabaseManager = null;
	public static LogDatabaseManager getLogDatabaseManager(Context context) {
		if (logDatabaseManager == null) {
			logDatabaseManager = new LogDatabaseManager(context);

		}
		return logDatabaseManager;
	}

	private  LogDatabaseManager(Context context) {

		
		this.logDatabaseHelper = LogDatabaseHelper
				.getLogdatabaseHelperInstance(context);

	}


	public void open() {

		try {
			this.logSQLiteDatabase = logDatabaseHelper.getWritableDatabase();
		
		} catch (SQLiteException e) {
			e.printStackTrace();
			
		}
	}

	
	public void close() {
		this.logDatabaseHelper.close();


	}

	public void check() {
		if (this.logSQLiteDatabase == null || !this.logSQLiteDatabase.isOpen()) {
			this.open();
		}
	}

	public synchronized long SaveLog(int year,int month,int day,int hour,int minute, String pkname,String content,int type) {

		check();
		long flag = -1;
		Cursor cursor = null;
		
			try {
				ContentValues cv = new ContentValues();
				cv.put("year", "" + year);
				cv.put("month", "" + month);
				cv.put("day", "" + day);
				cv.put("minute", minute);
				cv.put("hour", hour);
				cv.put("pkname", pkname);
				cv.put("content", content);
				cv.put("type", type);
				flag = this.logSQLiteDatabase.insert(this.tableName, null, cv);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			
				flag = -1;
			}
		
		return flag;
	}


//	public synchronized void deleteLog(LogFormat log) {
//		check();
//		int year = log.getYear();
//		int month = log.getMonth();
//		int day = log.getDay();
//		LogsObject logsObject = this.getLogsObjectWithCheck(year, month, day);
//		logsObject.deleteLog(log);
//		boolean hasLog = false;
//		for (int i = 0; i < logsObject.getOneDayLogs().size(); i++) {
//			if (logsObject.getOneDayLogs().get(i).size() != 0) {
//				hasLog = true;
//				break;
//			}
//		}
//		if (!hasLog) {
//			LogsObject logsObjectm = this.getLogsObjectWithCheck(year, month,
//					indicator);
//			logsObjectm.setfalseIndicatorForOneDay(day);
//
//			this.justUpdateLogsObject(logsObjectm);
//		}
//		this.justUpdateLogsObject(logsObject);
//
//	}
	


	
	
	public synchronized ArrayList<Log_MY> getLogs(int type) {
		check();
		Log_MY log=null;
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute=null;
		String content=null;
		String pkname=null;
		ArrayList<Log_MY> logs=new ArrayList<Log_MY>();
		Cursor cursor = null;
		try {
			Log.d("", "查询数据");
			String sql;
			switch (type) {
			case ALL_Log:
				sql = "select * from " + this.tableName ;
				cursor = logSQLiteDatabase.rawQuery(sql, null);
				break;
			case OP:
				 sql = "select * from " + this.tableName +"where type="+type;
				cursor = logSQLiteDatabase.rawQuery(sql, null);
				break;
			case WATCH:
				sql = "select * from " + this.tableName +"where type="+type;
			 cursor = logSQLiteDatabase.rawQuery(sql, null);
					break;
		
			}
			
		

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		   Log.d("", "数据大小"+cursor.getCount());
			while (cursor.getCount()!=0&&!cursor.isLast()) {
              
				cursor.moveToNext();
				try{
					year=cursor.getString(cursor.getColumnIndex("year"));
					month=cursor.getString(cursor.getColumnIndex("month"));
					day=cursor.getString(cursor.getColumnIndex("day"));
                    hour=cursor.getString(cursor.getColumnIndex("hour"));
                    minute=cursor.getString(cursor.getColumnIndex("minute"));
    			    content = cursor.getString(cursor
    						.getColumnIndex("content"));
    			    pkname=cursor.getString(cursor
						.getColumnIndex("pkname"));
    				Log.d("数据", year+month+content);
    				  log=new Log_MY(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute), pkname,content, type);
    		    		logs.add(log);	
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			
		}
			Log.d("最终获取数据大小", ""+logs.size());
			return logs;

	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
	

	
}
