package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view;

import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter.PresenterMVP;

public interface BaseView {
	
	int getLayout();

	PresenterMVP inject(ApplicationFrameworkMVP component);
	
	void setupView();
	
	
}