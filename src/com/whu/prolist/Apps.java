package com.whu.prolist;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

public class Apps implements Serializable {

	String pkgName;

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public String[] getPermission() {
		return permission;
	}

	public void setPermission(String[] permission) {
		this.permission = permission;
	}

	String name;
	Drawable icon;
	String[] permission;

	public Apps(String pkgname, String name, Drawable icon, String[] permission) {
		this.pkgName = pkgname;
		this.name = name;
		this.icon = icon;
		this.permission = permission;
	}

}
