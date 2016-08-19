package com.lsx.myshare;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.robv.android.xposed.XSharedPreferences;
import android.R.bool;
import android.R.string;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.UserDictionary.Words;

/**
 * @author Li Jiansong
 * @date:2015-7-15  上午10:01:03
 * @version :
 *
 *与xposed模块信息交互、共享
 *
 */
public class Share {
	private static  SharedPreferences mPreferences=null;
	private static  XSharedPreferences mXPreferences=null;
	private Context context;
	public Share(){
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();
	}

	public void saveShare(String key, String value) {
		Editor edit = mPreferences.edit();
		edit.putString(key, value);
		edit.commit();

	}
	public void reloadX() {
		// TODO Auto-generated method stub
		if(mXPreferences!=null){
			mXPreferences.reload();//Reload the settings from file if they have changed.
		}
	}

	public Share(Context context){
		this.context=context;
		this.mPreferences=context.getSharedPreferences("list", 1);
	}//makeWorldReadable
	public HashMap<String, String> getallX(String tag) {
		HashMap<String, String> pkg=new HashMap<String, String>();
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();	
		if(mPreferences!=null){
			
			HashMap<String, String> pkglist=(HashMap<String, String>)mPreferences.getAll();
			Iterator iter=pkglist.entrySet().iterator();
		    while(iter.hasNext())
		    {
		    	Map.Entry<String, String> entry=(Map.Entry<String, String>)iter.next();
		    	String key=entry.getKey();
		    	String value=entry.getValue();
		    	if(key.contains(".")&&value.contains(tag))
		    	{
		    		
		    		pkg.put(key, value);
		    		
		    	}
		    }
			
			return pkg;
		}
		else if(mXPreferences!=null){
			HashMap<String, String> pkglist=(HashMap<String, String>)mXPreferences.getAll();
			Iterator iter=pkglist.entrySet().iterator();
		    while(iter.hasNext())
		    {
		    	Map.Entry<String, String> entry=(Map.Entry<String, String>)iter.next();
		    	String key=entry.getKey();
		    	String value=entry.getValue();
		    	if(key.contains(".")&&value.contains(tag))
		    	{
		    		
		    		pkg.put(key, value);
		    		
		    	}
		    }
		    return pkg;
		}
		return pkg;
	}
	public String getStringX(String key, String defValue){
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();	
		if(mPreferences!=null){
			return mPreferences.getString(key, defValue);
		}
		else if(mXPreferences!=null){
			return mXPreferences.getString(key, defValue);
		}
		return defValue;
	}
	
	public String getShare(String key) {
		return mPreferences.getString("" + key, " ");

	}
	public int getIntX(String key,int defValue){
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();
		if(mPreferences!=null){
			return mPreferences.getInt(key, defValue);
		}else if(mXPreferences!=null){
			return mXPreferences.getInt(key, defValue);
		}
		return defValue;
	}
	
	public boolean getBooleanX(String key,boolean defValue){
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();
		if(mPreferences!=null){
			return mPreferences.getBoolean(key, defValue);
		}
		else if(mXPreferences!=null){
			return mXPreferences.getBoolean(key, defValue);
		}
		return defValue;
	}
	
	public void setStringX(String key, String value){
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();
		Editor editor=null;
		if(mPreferences!=null){
			editor=mPreferences.edit();
		}else if(mXPreferences!=null){
			editor=mXPreferences.edit();
		}
		if(editor!=null){
			editor.putString(key, value);
			editor.commit();
		}
	}
	
	public void setBooleanX(String key, boolean value) {
		mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();
        Editor editor = null;
        if (mPreferences != null) {
            editor = mPreferences.edit();
        } else if (mXPreferences != null) {
            editor = mXPreferences.edit();
        }

        if (editor != null) {
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void setIntX(String key, int value) {
    	mXPreferences=new XSharedPreferences("com.lsx.main","list");
		mXPreferences.makeWorldReadable();
		this.reloadX();
        Editor editor = null;
        if (mPreferences != null) {
            editor = mPreferences.edit();
        } else if (mXPreferences != null) {
            editor = mXPreferences.edit();
        }

        if (editor != null) {
            editor.putInt(key, value);
            editor.commit();
        }
    }

	public HashMap<String, String> getall() {
		// TODO Auto-generated method stub
		HashMap<String, String> src = new HashMap<String, String>();
		src.clear();
		HashMap<String, String> pkglist=(HashMap<String, String>)mPreferences.getAll();
		Iterator iter=pkglist.entrySet().iterator();
	    while(iter.hasNext())
	    {
	    	Map.Entry<String, String> entry=(Map.Entry<String, String>)iter.next();
	    	String key=entry.getKey();
	    	if(key.contains("."))
	    	{
	    		String value=entry.getValue();
	    		src.put(key, value);
	    		
	    	}
	    }
	    return src;
	
	}

	public void delete(String key) {
		// TODO Auto-generated method stub
		Editor edit = mPreferences.edit();
		edit.remove(key);
		edit.commit();

	}
}
