package com.whu.rzjl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.provider.Settings.System;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsx.main.R;
import com.whu.database.LogDatabaseManager;
import com.whu.log.Log_MY;
import com.whu.prolist.Apps;
import com.whu.yygl.AppInfo;

public class LoglistAdapter extends BaseAdapter {

	
	private static final int ALL_Log = 0;
	private static final int OP = 1;
	private static final int WATCH = 2;
	private ArrayList<Log_MY> logs;
	private AppInfo appInfo;
	public ArrayList<Log_MY> getLogs() {
		return logs;
	}
   LogDatabaseManager myLogDatabaseManager;
	public void setLogs(ArrayList<Log_MY> logs) {
		this.logs = logs;
	}

	private Context context;
	private Log_MY log;


	public LoglistAdapter(Context context,int type) {
     
	 Log.d("", "适配日志");
		this.context = context;
		this.myLogDatabaseManager=LogDatabaseManager.getLogDatabaseManager(context);
		this.logs= myLogDatabaseManager.getLogs(type);
		this.appInfo=new AppInfo(context);
		Log.d("",""+logs.size());

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.logs.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.logs.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View item = inflater.inflate(R.layout.log_item, null);
		log=this.logs.get(position);
		ImageView logo = (ImageView) item.findViewById(R.id.log_logo);
		TextView time = (TextView) item.findViewById(R.id.time);
		TextView content = (TextView) item.findViewById(R.id.content);
		ImageView safelogo=(ImageView)item.findViewById(R.id.safeLogo);
	    String name=appInfo.getAppName(log.getPkname());
	    String t=log.getYear()+"年"+log.getMonth()+"月"+log.getDay()+"日"+log.getHour()+"时"+log.getMinute()+"分";
	    time.setText(t); 
	    content.setText(log.getContent());
	    logo.setImageDrawable(appInfo.getAppIcon(log.getPkname()));
		return item;
	}

}
