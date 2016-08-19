package com.whu.main;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.lsx.main.R;
import com.lsx.myshare.Share;
import com.whu.about.AboutActivity;
import com.whu.aqpz.Safe;
import com.whu.help.HelpActivity;
import com.whu.rzjl.rzgl;
import com.whu.smshook.ServerHooker;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	private static  boolean open=true;
	private static String flag="保护ing...";
	private ImageButton aqpz;
	private ImageButton rzjl;
	private ImageButton yygl;
	private ImageButton jhrw;
	private  GuideView  logoamination;
	public   Share myshare;
	
	public   Context context=MainActivity.this;
	private void init()
	{
		aqpz=(ImageButton)findViewById(R.id.aqpz_bt);
		rzjl=(ImageButton)findViewById(R.id.rzjlbt);
		yygl=(ImageButton)findViewById(R.id.yyglbt);
		jhrw=(ImageButton)findViewById(R.id.jhrwbt);
		logoamination=(GuideView)findViewById(R.id.dynamic);
	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		myshare=new Share(context);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=df.format(new Date());
     	String seed = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
        myshare.saveShare("seed", seed);  
		yygl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, com.whu.yygl.yygl.class);
				startActivity(intent);
			}
		});
		aqpz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			 	// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, Safe.class);
				startActivity(intent);
			}
		});
		rzjl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, rzgl.class);
				startActivity(intent);
			}
		});
		jhrw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, com.whu.jhrw.jhrw.class);
				startActivity(intent);
			}
		});
		
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, "状态:"+flag);  
        menu.add(Menu.NONE, Menu.FIRST + 2, 0, "帮助"); 
        menu.add(Menu.NONE,Menu.FIRST+3,0,"关于Protect Privacy");
		return true;
	}
 
	 public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        switch(item.getItemId())
	        {
	       
	        case Menu.FIRST+1:     
	        	 
	        	open=!open;
	        Log.d("显示", ""+open);
	         //  logoamination.setFlag(open);
	           if(open)
	           { flag="保护ing...";
	           item.setTitle("状态:"+flag);
	              myshare.saveShare("smsf", ""+true);
	           }  else
	           {
	        	   flag="休眠ing...";
	             item.setTitle("状态:"+flag);
		        //Intent intent2 = new Intent();  
	    		//intent2.setClass(MainActivity.this,GradientActivity.class);    				
	    		//startActivity(intent2); 
	             myshare.saveShare("smsf", ""+false);
	           }
	           
	            break;
	        case Menu.FIRST+2:
	        {
	        	Intent intent=new Intent();
	        	intent.setClass(MainActivity.this, HelpActivity.class);
	        	startActivity(intent);
	        	
	        }
	            break;
	        case Menu.FIRST+3:
	        {
	        	
	        	Intent intent=new Intent();
	        	intent.setClass(MainActivity.this, AboutActivity.class);
	        	startActivity(intent);
	        	
	        	
	        } 
	            break;
	         default:
	        }
	        return true;
	    }
	    
 
}
