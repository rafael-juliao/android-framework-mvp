package com.lfyt.mobile.android.frameworkmvp.models.util;

import android.content.Context;

import javax.inject.Inject;

public class StringAPI {
	
	
	private final Context mContext;

	@Inject
	public StringAPI(Context context){
		mContext = context;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// Find String By Id and format with parameters
	///////////////////////////////////////////////////////////////////////////
	
	public String id(int id){
		return mContext.getString(id);
	}
	
	public String id(int id, Object... params){
		return String.format( id(id), params);
	}
	
	public String id(String string, Object... params){
		return String.format( string, params);
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// Build String using commas ("my"," account, "is", 4,
	///////////////////////////////////////////////////////////////////////////
	
	public String append(Object... objects) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object o : objects) {
			stringBuilder.append(String.valueOf(o));
		}
		return stringBuilder.toString();
	}
	
	
	
	

	

	///////////////////////////////////////////////////////////////////////////
	// Check isEmpty
	///////////////////////////////////////////////////////////////////////////

	public boolean isEmpty(String str){
		return str.replace(" ","").length() == 0;
	}


	public boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}
