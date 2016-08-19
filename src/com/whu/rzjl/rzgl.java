package com.whu.rzjl;

//Download by http://www.codefans.net
import java.util.ArrayList;
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
import com.whu.database.LogDatabaseManager;
import com.whu.prolist.ProlistAdapter;

public class rzgl extends Activity {
	private ListView logListView;
	private static final int ALL_Log = 0;
	private static final int OP = 1;
	private static final int WATCH = 2;
	private LoglistAdapter loglistAdapter;
	private String[] names = new String[] { "添加信赖", "查看详细" };
	
	private Context context = rzgl.this;
	private ArrayList<String> pkg;
	//private MyHandler myHand;
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "清空日志");  
       
		return true;
	}
	 public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        switch(item.getItemId())
	        {
	        case Menu.FIRST+1:     
	        	 LogDatabaseManager logDatabaseManager=LogDatabaseManager.getLogDatabaseManager(rzgl.this);
	        logDatabaseManager.clear();
	        
	            break;
	
	       
	        }
	        return true;
	    }
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rzjl);
		//myHand = new MyHandler();
		//LogDatabaseManager logDatabaseManager=LogDatabaseManager.getLogDatabaseManager(rzgl.this);
	    logListView = (ListView) findViewById(R.id.logList);
		loglistAdapter = new LoglistAdapter(rzgl.this,
		ALL_Log);
		logListView.setAdapter(loglistAdapter);

//		logListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> adapter, View arg1,
//					int position, long arg3) {
//				// TODO Auto-generated method stub
//                 switch (state) {
//				case all:
//					com.whu.prolist.Apps detail = (com.whu.prolist.Apps) loglistAdapter.getItem(position);
//					pkgname = detail.getPkgName();
//					Intent intent = new Intent();
//					intent.setClass(context, ItemAppDetail.class);
//					intent.putExtra("pkgname", pkgname);
//					startActivity(intent);
//					break;
//                case sms:
//                	com.whu.prolist.Apps d = (com.whu.prolist.Apps) prolistAdapter.getItem(position);
//    				pkgname = d.getPkgName();
//    				showDialog(0);
//					break;
//                case gps:
//                	com.whu.prolist.Apps dd = (com.whu.prolist.Apps) prolistAdapter.getItem(position);
//    				pkgname = dd.getPkgName();
//                	showDialog(0);
//               	break;
//				
//				}
//				
//			}
//
//		});

	}
//	private class MyHandler extends Handler {
//
//		@Override
//		public void handleMessage(Message msg) {
//			// TODO Auto-generated method stub
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case 0:
//				myshare.saveShare(pkgname, pkgname);
//				Toast.makeText(context, "成功信赖!", 3000).show();
//				 prolistAdapter = new ProlistAdapter(yygl.this, getPackageManager(),
//							state);
//					proListView.setAdapter(prolistAdapter);
//				break;
//			case 1:
//				Intent intent = new Intent();
//				intent.setClass(context, ItemAppDetail.class);
//				intent.putExtra("pkgname", pkgname);
//				startActivity(intent);
//				break;
//
//			default:
//				break;
//			}
//		}
//	}
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
				//		myHand.sendEmptyMessage(which);
					}
				});
		// 创建对话框
		return b.create();

	}

}
