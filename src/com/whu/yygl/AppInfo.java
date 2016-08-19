package com.whu.yygl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

public class AppInfo {
	Context context;
	PackageManager pm;

	public AppInfo(Context context) {
		this.context = context;
		pm = context.getPackageManager();
	}

	/*
	 * ��ȡ���� ͼ��
	 */
	public Drawable getAppIcon(String packname) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			return info.loadIcon(pm);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;

	}

	/*
	 * ��ȡ����İ汾��
	 */
	public String getAppVersion(String packname) {

		try {
			PackageInfo packinfo = pm.getPackageInfo(packname, 0);
			return packinfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();

		}
		return packname;
	}

	/*
	 * ��ȡ���������
	 */
	public String getAppName(String packname) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			return info.loadLabel(pm).toString();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return packname;
	}

	/*
	 * ��ȡ�����Ȩ��
	 */
	public String[] getAppPremission(String packname) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(packname,
					PackageManager.GET_PERMISSIONS);
			// ��ȡ�����е�Ȩ��
			return packinfo.requestedPermissions;

		} catch (NameNotFoundException e) {
			e.printStackTrace();

		}
		return null;
	}

	/*
	 * ��ȡ�����ǩ��
	 */
	public String getAppSignature(String packname) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(packname,
					PackageManager.GET_SIGNATURES);
			// ��ȡ�����е�Ȩ��
			return packinfo.signatures[0].toCharsString();

		} catch (NameNotFoundException e) {
			e.printStackTrace();

		}
		return null;
	}
}