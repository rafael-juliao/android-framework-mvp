package com.lfyt.mobile.android.frameworkmvp.archtecture.service;

import android.support.annotation.CallSuper;

import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationLifecycleAPI;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by rafaeljuliao on 17/05/18.
 */

public abstract class ForegroundService extends BackgroundService {


	///////////////////////////////////////////////////////////////////////////
	// Dependencies
	///////////////////////////////////////////////////////////////////////////

	@Inject ApplicationLifecycleAPI applicationStateAPI;



	///////////////////////////////////////////////////////////////////////////
	// Foreground Service Lifecycle
	///////////////////////////////////////////////////////////////////////////



	@Override
	protected void onServiceCreated() {
		super.onServiceCreated();
		applicationStateAPI.subscribe(codeApplicationState);
	}



	///////////////////////////////////////////////////////////////////////////
	// Foreground Control
	///////////////////////////////////////////////////////////////////////////


	@CallSuper
	protected void onApplicationClosed() {
		startForegroundWhenAppInBack();
	}

	@CallSuper
	protected void onApplicationOpen() {
		stopForegroundWhenAppInFront();
	}

	protected void startForegroundWhenAppInBack() {

	}

	protected void stopForegroundWhenAppInFront(){

	}




	///////////////////////////////////////////////////////////////////////////
	// Hide notification when application started
	///////////////////////////////////////////////////////////////////////////

	private Object codeApplicationState = new Object(){

		@Subscribe
		public void onApplicationStarted(ApplicationLifecycleAPI.ApplicationStarted applicationStarted){
			onApplicationOpen();
		}


		@Subscribe
		public void onApplicationStopped(ApplicationLifecycleAPI.ApplicationStopped applicationStarted){
			onApplicationClosed();
		}

	};




}
