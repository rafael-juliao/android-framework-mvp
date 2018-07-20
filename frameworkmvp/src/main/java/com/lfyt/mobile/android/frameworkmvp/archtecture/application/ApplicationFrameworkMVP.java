package com.lfyt.mobile.android.frameworkmvp.archtecture.application;

/**
 * Created by rafaeljuliao on 13/10/17.
 */

public interface ApplicationFrameworkMVP<ApplicationComponent, ViewComponent> {
	void setupDependencyInjectionComponents();
	ApplicationComponent getApplicationComponent();
	ViewComponent getViewComponent();
}
