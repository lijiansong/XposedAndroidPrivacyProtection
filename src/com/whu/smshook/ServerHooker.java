package com.whu.smshook;


import java.lang.reflect.Field;

import com.lsx.myshare.Share;
import com.whu.database.LogDatabaseManager;
import com.whu.main.MainActivity;

import android.content.Context;
import android.graphics.Shader;
import android.net.Uri;

import android.text.SpannableStringBuilder;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * @author Li Jiansong
 * @date:2015-5-8  下午3:41:57
 * @version :
 *
 *分析：
 *可以考虑拦截类com.android.mms.transaction.SmsSingleRecipientSender的
 *SmsSingleRecipientSender.sendMessage()方法
 *或者其构造方法经测试行不通
 *进行测试，针对WorkingMessage.send()方法的加密部分进行解密
 *
 *SmsSingleRecipientSender.java—继承自SmsMessageSender，
 *它针对一个收信人，调用Frameworks层接口发送信息，对于Mms应用来说，这是发送短信的最后一站，
 *对就是说对于应用来说，它会把短信发送出去。
 *
 *思路2：
 *可以考虑拦截SmsMessageSender类的mMessageText变量
 */
public class ServerHooker implements IXposedHookLoadPackage{
	
	private static final String PACKAGE_MMS="com.android.mms";
	private static final String PACKAGE_TRANS="com.android.mms.transaction";
	private static final String CLASSNAME_SEND="com.android.mms.data.WorkingMessage";
	private  static Share share=new Share();
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
		XposedBridge.log("Loaded app: "+lpparam.packageName);
		if(!lpparam.packageName.equals(PACKAGE_MMS)){
			
			return ;
			
		}
		//XposedBridge.log("------------目前在transaction包中---------------");
		//所拦截的类com.android.mms.transaction.SmsSingleRecipientSender的构造方法定义如下
//		 public SmsSingleRecipientSender(Context context, String dest, String msgText, long threadId,
//		            boolean requestDeliveryReport, Uri uri) {
//		        super(context, null, msgText, threadId);
//		        mRequestDeliveryReport = requestDeliveryReport;
//		        mDest = dest;
//		        mUri = uri;
//		    }
		try {
//			final Class<?> sendClazz=XposedHelpers.findClass(CLASSNAME_SEND,
//					lpparam.classLoader);
			final Class<?> serverClazz=XposedHelpers.findClass(PACKAGE_TRANS+".SmsMessageSender",
					lpparam.classLoader);
			XposedHelpers.findAndHookMethod(serverClazz, "sendMessage", 
					long.class,
					new XC_MethodHook(){
				@Override
				protected void beforeHookedMethod(MethodHookParam param)
						throws Throwable {
					// TODO Auto-generated method stub
					//super.beforeHookedMethod(param);
					XposedBridge.log("----发送时，Client端开始拦截WorkingMessage的send方法-------");
					
					
					
					XposedBridge.log("----成功拦截WorkingMessage的send方法------");
					
					
//					XposedBridge.log("----开始分配DES加、解密密钥--------");


					
					
					XposedBridge.log("-------发送时，Server端开始拦截sendMessage进行解密--------");
					XposedBridge.log("-------开始分配DES加、解密密钥--------");

					
					
					Field field=XposedHelpers.findField(serverClazz, "mMessageText");
					String messageText=(String) field.get(param.thisObject);
					String msgTxt=messageText.toString();

					//String msgTxt=(String) param.args[2];
					 
					XposedBridge.log("---------madan----"+share.getStringX("smsf", "错啦"));
		        
					
				
				if(share.getStringX("smsf", "错啦").equals("true"))
				{
					//解密运算
//					char []array1=msgTxt.toCharArray();//获取字符数组
//					for(int i=0;i<array1.length;i++){
//						array1[i]=(char) (array1[i]^20000);//再次对每个数组元素进行异或运算解密
//					}
					String seed=share.getStringX("seed", "");
					XposedBridge.log("---------解密密钥----"+seed);
					XposedBridge.log("---------要求解密的的密文----"+msgTxt);
					String msg=new com.whu.endecrypt.AESUtil().decrypt(seed, msgTxt);
					XposedBridge.log("---------解密后----"+msg);
					field.set(param.thisObject, msg);
					}
				
				}
//					Field field=XposedHelpers.findField(sendClazz, "mText");
//					SpannableStringBuilder text=(SpannableStringBuilder) field.get(param.thisObject);
//					field.set(param.thisObject,msgText+"\n"+"test");
					//XposedBridge.log("---------发送时，Server端解密成功-----------");

				
				@Override
						protected void afterHookedMethod(MethodHookParam param)
								throws Throwable {
							// TODO Auto-generated method stub
							super.afterHookedMethod(param);
						}
				
			});
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			XposedBridge.log(t);
		}
	}

}
