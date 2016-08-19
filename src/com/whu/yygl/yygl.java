package com.whu.yygl;

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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lsx.main.R;
import com.lsx.myshare.Share;
import com.whu.database.LogDatabaseManager;
import com.whu.prolist.ProlistAdapter;

public class yygl extends Activity {

	private static final int ALL_PRO = 0;
	private ListView proListView;
	private static final int SMS = 1;
	private static final int GPS = 2;
	private ProlistAdapter prolistAdapter;
	private String[] names = new String[] { "添加保护", "查看详细" };
	private String pkgname = null;
	private Context context = yygl.this;
	private static final int all=0;
	private static final int sms=1;
	private static final int gps=2;
	private ArrayList<String> pkg;
	private AppInfo appInfo;
	private int state=0;
	private Share myshare;
	
	private MyHandler myHand;
	private LogDatabaseManager logDatabaseManager=LogDatabaseManager.getLogDatabaseManager(yygl.this);
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "全部应用");  
        menu.add(Menu.NONE, Menu.FIRST + 2, 0, "短信应用"); 
        menu.add(Menu.NONE,Menu.FIRST+3,0,"GPS应用");
		return true;
	}
	 public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        switch(item.getItemId())
	        {
	        case Menu.FIRST+1:     
	           state=0;
	        prolistAdapter = new ProlistAdapter(yygl.this, getPackageManager(),
					ALL_PRO);
			proListView.setAdapter(prolistAdapter);
	            break;
	        case Menu.FIRST+2:
		           state=1;
	        prolistAdapter = new ProlistAdapter(yygl.this, getPackageManager(),
					SMS);
			proListView.setAdapter(prolistAdapter);
	            break;
	        case Menu.FIRST+3:
		           state=2;
	        prolistAdapter = new ProlistAdapter(yygl.this, getPackageManager(),
					GPS);
			proListView.setAdapter(prolistAdapter);
	            break;
	       
	        }
	        return true;
	    }
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yygl);
		myHand = new MyHandler();
		appInfo=new AppInfo(context);
		myshare = new Share(context);
		proListView = (ListView) findViewById(R.id.pro);
	prolistAdapter = new ProlistAdapter(yygl.this, getPackageManager(),
			ALL_PRO);
		proListView.setAdapter(prolistAdapter);

		proListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
                 switch (state) {
				case all:
					com.whu.prolist.Apps detail = (com.whu.prolist.Apps) prolistAdapter.getItem(position);
					pkgname = detail.getPkgName();
					Intent intent = new Intent();
					intent.setClass(context, ItemAppDetail.class);
					intent.putExtra("pkgname", pkgname);
					startActivity(intent);
					break;
                case sms:
                	com.whu.prolist.Apps d = (com.whu.prolist.Apps) prolistAdapter.getItem(position);
    				pkgname = d.getPkgName();
    				showDialog(0);
					break;
                case gps:
                	com.whu.prolist.Apps dd = (com.whu.prolist.Apps) prolistAdapter.getItem(position);
    				pkgname = dd.getPkgName();
                	showDialog(0);
               	break;
				
				}
				
			}

		});

	}
	private class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				String tag=null;
				switch (state) {
				case SMS:
					tag="sms";
					break;
				case GPS:
					tag="gps";
					break;
				}
				myshare.saveShare(pkgname, tag);
				Toast.makeText(context, "成功保护!", 3000).show();
			  Calendar calendar=Calendar.getInstance();
			  String s=null;
			 switch (state) {
			case sms:
				 s="操作:保护短信敏感信息 目标App:";
				break;
           case gps:
				s="操作:保护GPS敏感信息 目标App:";
				break;
			}
			 logDatabaseManager.SaveLog(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1 , calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), pkgname,s+appInfo.getAppName(pkgname), 1);
		  
			 prolistAdapter = new ProlistAdapter(yygl.this, getPackageManager(),
							state);
					proListView.setAdapter(prolistAdapter);
				break;
			case 1:
				Intent intent = new Intent();
				intent.setClass(context, ItemAppDetail.class);
				intent.putExtra("pkgname", pkgname);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	}
	@Override
	public Dialog onCreateDialog(int id, Bundle state) {
		// 判断需要生成哪种类型的对话框

		Builder b = new AlertDialog.Builder(this);
		// 设置对话框的图标
		// b.setIcon(R.drawable.tools);
		// 设置对话框的标题
		b.setTitle("操作");
		// 创建一个List对象，List对象的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("personName", names[i]);
			listItems.add(listItem);
		}
		// 创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.choose_dialog,
				new String[] { "personName", "header" }, new int[] { R.id.name,
						R.id.header });

		// 为对话框设置多个列表
		b.setAdapter(simpleAdapter
		// 为列表项的单击事件设置监听器
				, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						myHand.sendEmptyMessage(which);
					}
				});
		// 创建对话框
		return b.create();

	}

}
