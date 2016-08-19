package com.whu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LogDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "log.db";
	private static final int DATABASE_VERSION = 1; 
	// private Context context; 
	private static LogDatabaseHelper DB_HELPER = null;
	private final static String createTable = "create table if not exists log("
			+ "_id integer primary key autoincrement,year String,month String,day String," +
			" hour String,minute String,pkname String,content String,type String"
			+ ")";
//	private final static String createTable = "create table if not exists logdatabase("
//			+ "_id integer primary key autoincrement,year String,month String,day String,logsobject text"
//			+ ")";
	public static LogDatabaseHelper getLogdatabaseHelperInstance(Context context) {
		if (DB_HELPER == null) {
			DB_HELPER = new LogDatabaseHelper(context);
		}

		return DB_HELPER;
	}
	private LogDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// this.context = context;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(createTable);
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
