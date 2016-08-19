package com.whu.endecrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Base64;

/**
 * @author Li Jiansong
 * @date:2015-7-14  上午11:29:25
 * @version :
 *
 */
public class MD5 {
	
	public static String getMd5(String str){
		byte[] b=null;
		String mMd5Result=null;
		StringBuffer buf = new StringBuffer(""); 
		if(str!=null&&str.length()!=0){
			try {
				MessageDigest md=MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				b=md.digest();
				for(int offset=0;offset<b.length;offset++){
					int i=b[offset];
					if(i<0)
						i+=256;
					if(i<16)
						buf.append("0");
					buf.append(Integer.toHexString(i));//以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
					mMd5Result=buf.toString();
					//mMd5Result=buf.toString().substring(8,24);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mMd5Result;
	}
	public static String getMD5Str(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String seed=df.format(new Date());
		String strBase64 = new String(Base64.encode(seed.getBytes(), Base64.DEFAULT));
		return strBase64;
	}
}
