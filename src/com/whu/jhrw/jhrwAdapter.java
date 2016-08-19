package com.whu.jhrw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lsx.myshare.Share;

import com.lsx.main.R;
import com.whu.prolist.Apps;

public class jhrwAdapter extends BaseAdapter {

	private List<Apps> proList;
     private HashSet<Integer> pss;
	public List<Apps> getProList() {
		return proList;
	}

	public void setProList(List<Apps> proList) {
		this.proList = proList;
	}

	private LayoutInflater layoutInflater;
	private Context context;
	private PackageManager pkManager;
	private static final int SMS = 1;
	private static final int GPS = 2;
	private static final int ALL_PRO = 0;
	private Share my;
    
	public jhrwAdapter(Context context, PackageManager pkManager, int state) {

		this.context = context;
		this.pkManager = pkManager;
		this.proList = new ArrayList<Apps>();
		this.my=new Share(context);
		this.pss=new HashSet<Integer>();
		Intent query = new Intent(Intent.ACTION_MAIN);
		query.addCategory("android.intent.category.LAUNCHER");
		List<ResolveInfo> resolves = pkManager.queryIntentActivities(query,
				PackageManager.GET_ACTIVITIES);
		if(state<3)
		{
		for (int i = 0; i < resolves.size(); i++) {

			ResolveInfo info = resolves.get(i);
			String name = info.loadLabel(pkManager).toString();
			String pakagename = info.activityInfo.packageName;
			String[] permission;
			Drawable icon = info.loadIcon(pkManager);
			Apps app = null;

			try {
				permission = pkManager.getPackageInfo(
						info.activityInfo.packageName,
						PackageManager.GET_PERMISSIONS).requestedPermissions;//获取权限列表

				switch (state) {
				case ALL_PRO:
					app = new Apps(pakagename, name, icon, permission);
					
					break;
				case SMS:
					for (String perm : permission) {

						if (perm.equals("android.permission.RECEIVE_SMS")) {

							app = new Apps(pakagename, name, icon, permission);
							break;
						}

					}
					break;
				case GPS:
					for (String perm : permission) {

						if (perm.equals("android.permission.ACCESS_FINE_LOCATION")
								| perm.equals("")
								| perm.equals("")
								| perm.equals("") | perm.equals("")) {

							app = new Apps(pakagename, name, icon, permission);
							break;
						}
					}
					break;
				default:
					
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (app != null) {
				proList.add(app);
			}

		}}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.proList.size();
	}
  public void setPss(int p)
  {
	  if(pss.contains(p))
	  {
		  pss.remove(p);
	  }
	  else
		  pss.add(p);
  }
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.proList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View item = inflater.inflate(R.layout.prolist_item, null);
		ImageView logo = (ImageView) item.findViewById(R.id.pro_logo);
		TextView name = (TextView) item.findViewById(R.id.appname);
		TextView pkg_name = (TextView) item.findViewById(R.id.apppkg);
		ImageView safelogo=(ImageView)item.findViewById(R.id.safeLogo);
	
		Apps app = this.proList.get(position);
		if(pss.contains(position))
			safelogo.setBackgroundResource(R.drawable.reliable);
		pkg_name.setText(app.getPkgName());
		name.setText(app.getName());
		logo.setImageDrawable(app.getIcon());

		return item;
	}

}
