package com.lfyt.mobile.android.frameworkmvp.models.util;

import android.os.Build;

import javax.inject.Inject;

public class VersionAPI {

	@Inject
	public VersionAPI(){
	}

	public boolean is_16_JellyBean(){return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;}

	public boolean is_19_KitKat(){return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;}

	public boolean is_21_Lollipop(){return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;}

	public boolean is_23_Marshmallow(){return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;}

	public boolean is_24_Nugget(){return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;}

	public boolean is_26_Oreo(){return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;}

}
