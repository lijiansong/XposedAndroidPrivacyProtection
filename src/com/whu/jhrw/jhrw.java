package com.whu.jhrw;

//Download by http://www.codefans.net
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lsx.main.R;
import com.whu.about.AboutActivity;
import com.whu.aqpz.Safe;
import com.whu.database.LogDatabaseManager;
import com.whu.help.HelpActivity;
import com.whu.prolist.Apps;
import com.whu.prolist.ProlistAdapter;
import com.whu.yygl.AppInfo;
import com.whu.yygl.ItemAppDetail;

public class jhrw extends Activity {
  private ImageButton ok;
  private ListView logListView;
  private jhrwAdapter lisAdapter;
  private Context context=jhrw.this;
  private ImageButton sms;
  private ImageButton gps;
  private ImageButton sys;
  private int type=1;
  private final int SYS=000;
  private final int GPS=111;
  private final int SMS=222;
	private String target;
  LogDatabaseManager logDatabaseManager=LogDatabaseManager.getLogDatabaseManager(jhrw.this);
private PackageManager pkManager;
protected Apps app;
//  private ListView 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jhrw);
		logDatabaseManager=LogDatabaseManager.getLogDatabaseManager(jhrw.this);
		ok=(ImageButton)findViewById(R.id.ok);
		logListView=(ListView)findViewById(R.id.listLogs);
		pkManager=getPackageManager();
		lisAdapter=new jhrwAdapter(context, pkManager, 55);
		logListView.setAdapter(lisAdapter);
		sms=(ImageButton)findViewById(R.id.SMS);
		gps=(ImageButton)findViewById(R.id.GPS);
		sys=(ImageButton)findViewById(R.id.SYS);
	
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Calendar calendar=Calendar.getInstance();
				String str=null;
				switch(type)
				{
				case SMS:
					str="对短信进行敏感信息定时保护,目标APP:"+target;
					break;
   				case GPS:
   					str="对GPS敏感信息定时保护,目标APP:"+target;
   					break;
   				case SYS:
   					str="系统定时运行时间配置,目标:系统";
   					break;
				
				
				}
				logDatabaseManager.SaveLog(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), app.getPkgName(), str, type);
			}
		});
		sms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				lisAdapter=new jhrwAdapter(context, pkManager, 1);
				logListView.setAdapter(lisAdapter);
				type=SMS;
			}
		});
		gps.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				lisAdapter=new jhrwAdapter(context, pkManager, 2);
				logListView.setAdapter(lisAdapter);
			type=GPS;
			}
		});
		sys.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				type=SYS;
				// TODO Auto-generated method stub
				lisAdapter=new jhrwAdapter(context, pkManager, 55);
				logListView.setAdapter(lisAdapter);
			}
		});
	     logListView.setOnItemClickListener(new OnItemClickListener() {

		

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
		     app=(Apps)lisAdapter.getItem(arg2);
		     target=app.getName();
			 lisAdapter.setPss(arg2);
			 lisAdapter.notifyDataSetChanged();
			}
		});
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
 
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "帮助"); 
        menu.add(Menu.NONE,Menu.FIRST+2,0,"关于Protect Privacy");
		return true;
	}
 
	 public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        switch(item.getItemId())
	        {
	        case Menu.FIRST+1:     
	       
	        {
	        	Intent intent=new Intent();
	        	intent.setClass(jhrw.this, HelpActivity.class);
	        	startActivity(intent);
	        	
	        }
	    		
	            break;
	        case Menu.FIRST+2:
 {
	        	
	        	Intent intent=new Intent();
	        	intent.setClass(jhrw.this, AboutActivity.class);
	        	startActivity(intent);
	        	
	        	
	        } 
	            break;
	       
	        }
	        return true;
	    }
}
