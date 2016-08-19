package com.whu.yygl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsx.main.R;


public class ItemAppDetail extends Activity {

	private String pkgName;
	private AppInfo appInfo;
	private Context context = ItemAppDetail.this;
	private ImageView icon;
	private TextView appName;
	private TextView apppermission;
	private TextView appedition;
	private TextView appSignature;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiangxi);
		Intent intent = getIntent();
		pkgName = intent.getStringExtra("pkgname");
		appInfo = new AppInfo(context);
		icon = (ImageView) findViewById(R.id.icon);
		appName = (TextView) findViewById(R.id.name);
		appedition = (TextView) findViewById(R.id.edition);
		apppermission = (TextView) findViewById(R.id.permission);
		// appSignature = (TextView) findViewById(R.id.signature);
		icon.setBackgroundDrawable(appInfo.getAppIcon(pkgName));
		appedition.setText(appInfo.getAppVersion(pkgName));
		// appSignature.setText(appInfo.getAppSignature(pkgName));
		appName.setText(appInfo.getAppName(pkgName));
		StringBuffer sb = new StringBuffer();
		for (String item : appInfo.getAppPremission(pkgName)) {
			sb.append(item);
			sb.append("\n");
		}
		apppermission.setText(sb.toString());

	}
}
