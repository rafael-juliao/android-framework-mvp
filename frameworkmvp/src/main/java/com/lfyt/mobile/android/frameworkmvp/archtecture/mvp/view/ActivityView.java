package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivityView extends AppCompatActivity{
	
	
	private ViewMVP viewMVP;
	
	
	////////////////////////////////
	////////// Lifecycle ///////////
	////////////////////////////////
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewMVP = new ViewMVP();
		viewMVP.setActivityView(this);
	}




	@Override
	public void onStart() {
		viewMVP.subscribe();
		super.onStart();
		viewMVP.start();
	}


	@Override
	public void onStop() {
		viewMVP.stop();
		super.onStop();
		viewMVP.unsubscribe();
	}


}
