package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view;

import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter.Presenter;

public interface ViewContract {
	
	int getLayout();

	Presenter inject(ApplicationFrameworkMVP component);
	
	void setupView();
	
	
}