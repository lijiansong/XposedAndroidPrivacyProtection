
package com.whu.about;

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

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_about);

		GradientDrawable grad = new GradientDrawable(// 渐变色 WHITE
				Orientation.TOP_BOTTOM, new int[] {
						Color.rgb(133, 224, 224),
						Color.rgb(51, 136, 103) });
		getWindow().setBackgroundDrawable(grad);
	
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		String html = "<big><font color ='red'>Protect Privacy</font></big><br/>"			
				+ "&nbsp;&nbsp;&nbsp;&nbsp;版本: 1.0<br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;最后更新: 2015-05-25<br/>"
					+ "<big><font color ='red'>开发队伍</font></big><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;武汉大学计算机学院SkyRunner"
				+ "<br/><big><font color ='red'>指导老师:</font></big><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;彭国军" ;
		CharSequence charSequence = Html.fromHtml(html);
		tv_title.setText(charSequence);
		tv_title.setMovementMethod(LinkMovementMethod.getInstance());

		TextView tv_info = (TextView) findViewById(R.id.tv_info);
		final String htmlInfo = "<html><head><title></title></head><body>"
				+ " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<big><font color ='red'>About the Author&project</font></big>"
				+ " <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>SkyRunner</b>"
				+ "   <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;队长：李世雄 队员 ：李坚松  湛昭豪 罗格  "
				+ " <b>^_^</b>"		
				+ " <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Android系统内部基于Binder的进程间通信机制，" +
				"极易导致敏感数据在系统内部传递过程中被恶意第三方窃取与篡改。" +
				"针对此类威胁，本作品在详细分析和深入理解敏感信息在系统内部产生、传递和使用整个流程的基础上，" +
				"设计并实现一种面向系统内部数据传递过程的敏感信息加密与保护方案，可有效防止敏感数据被恶意第三方窃取与篡改，" +
				"保障用户敏感数据在系统内部传输过程中的私密性及完整性。本作品的成功完成，" +
				"将有效阻断恶意第三方在Android系统内部对用户私密数据的攻击渠道，在保障用户敏感数据安全" +
				"方面具有重要应用价值和前景。"
				
			
				+ "</body></html>";
		CharSequence charSequenceInfo = Html.fromHtml(htmlInfo);
		tv_info.setText(charSequenceInfo);
		tv_info.setMovementMethod(LinkMovementMethod.getInstance());
	}
		

	

}
