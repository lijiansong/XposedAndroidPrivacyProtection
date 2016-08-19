package com.whu.aqpz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lsx.main.R;
import com.lsx.myshare.Share;
import com.whu.about.AboutActivity;
import com.whu.database.LogDatabaseManager;
import com.whu.help.HelpActivity;
import com.whu.main.MainActivity;
import com.whu.yygl.AppInfo;
import com.whu.yygl.ItemAppDetail;


public class Safe extends Activity {

	private String[] names = new String[] { "取消保护", "查看详细" };
	private Context context = Safe.this;
	private Share myshare;
	private ListView proListView;
	private BaseAdapter prolistAdapter;
	private com.whu.yygl.AppInfo appInfo;
	private MyHandler myHand;
	private String pkgName;
	private Map<String, String> data;
private LogDatabaseManager logDatabaseManager=LogDatabaseManager.getLogDatabaseManager(Safe.this);
	private ArrayList<String> pkg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aqpz);
		myshare = new Share(context);
		appInfo = new AppInfo(context);
		proListView = (ListView) findViewById(R.id.pro);
		prolistAdapter = new SafeAdapter();
		proListView.setAdapter(prolistAdapter);
		myHand = new MyHandler();
		proListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				pkgName = pkg.get(position);
				showDialog(0);
			}
		});

	}

	public void fresh() {
		prolistAdapter = new SafeAdapter();
		proListView.setAdapter(prolistAdapter);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fresh();

	}

	private class SafeAdapter extends BaseAdapter {

		public SafeAdapter() {

			data = (HashMap<String, String>) myshare.getall();
			pkg = new ArrayList<String>();
			for (Entry<String, String> d : data.entrySet()) {
				pkg.add(d.getKey());
			}
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View item = inflater.inflate(R.layout.prolist_item, null);
			ImageView logo = (ImageView) item.findViewById(R.id.pro_logo);
			TextView name = (TextView) item.findViewById(R.id.appname);
			TextView pkg_name = (TextView) item.findViewById(R.id.apppkg);

			pkg_name.setText(pkg.get(arg0));
			logo.setImageDrawable(appInfo.getAppIcon(pkg.get(arg0)));
			name.setText(appInfo.getAppName(pkg.get(arg0)));
			return item;
			// return;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pkg.size();
		}

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
	        	intent.setClass(Safe.this, HelpActivity.class);
	        	startActivity(intent);
	        	
	        }
	    		
	            break;
	        case Menu.FIRST+2:
 {
	        	
	        	Intent intent=new Intent();
	        	intent.setClass(Safe.this, AboutActivity.class);
	        	startActivity(intent);
	        	
	        	
	        } 
	            break;
	       
	        }
	        return true;
	    }
	private class MyHandler extends Handler {

	

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				myshare.delete(pkgName);
				fresh();
				  Calendar calendar=Calendar.getInstance();
					 logDatabaseManager.SaveLog(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1 , calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), pkgName, "操作:取消短信权限 目标App:"+appInfo.getAppName(pkgName), 1);
				Toast.makeText(context, "取消成功!", 3000).show();
				break;
			case 1:
				Intent intent = new Intent();
				intent.setClass(context, ItemAppDetail.class);
				intent.putExtra("pkgname", pkgName);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	}


	@Override
	public Dialog onCreateDialog(int id, Bundle state) {
	
		Builder b = new AlertDialog.Builder(this);
		
		b.setTitle("安全配置");
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("personName", names[i]);
			listItems.add(listItem);
		}
	
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.choose_dialog,
				new String[] { "personName", "header" }, new int[] { R.id.name,
						R.id.header });

		b.setAdapter(simpleAdapter
		
				, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						myHand.sendEmptyMessage(which);
					}
				});
	
		return b.create();

	}

}
