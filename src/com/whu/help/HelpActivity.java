
package com.whu.help;

import com.lsx.main.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置没标题
		setContentView(R.layout.activity_about);

		GradientDrawable grad = new GradientDrawable(// 渐变色 WHITE
				Orientation.TOP_BOTTOM, new int[] {
						Color.rgb(133, 224, 224),
						Color.rgb(51, 136, 103) });
		getWindow().setBackgroundDrawable(grad);
	
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		String html = "<big><font color ='red'>安全配置:</font></big><br/>"			
				+ "&nbsp;&nbsp;&nbsp;&nbsp;显示用户赋予权限的软件列表，即可获取隐私数据并信赖的软件列表," +
				"在该模块下可以取消改信赖，回收权限，同时也可查看其详细情况<br/>"
					+ "<big><font color ='red'>日志记录:</font></big><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;记录用户赋予软件信赖的敏感动作同时监测该软件对系统的保护情况"
				+ "<br/><big><font color ='red'>计划任务:</font></big><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;用于定制软件保护时间，定制敏感软件的获得信赖的时间段" 
				+"<br/><big><font color ='red'>应用管理:</font></big><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;显示系统应用列表,可选择显示涉及敏感信息的软件列表，查看详细情况或赋予信赖";
		CharSequence charSequence = Html.fromHtml(html);
		tv_title.setText(charSequence);
		tv_title.setMovementMethod(LinkMovementMethod.getInstance());

		
	}
		

	

}
