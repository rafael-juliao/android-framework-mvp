package com.lfyt.mobile.android.frameworkmvp.models.framework.webservice;

import com.lfyt.mobile.android.livemodel.Event;
import com.lfyt.mobile.android.livemodel.LiveModel;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rafaeljuliao on 04/05/18.
 */
@Singleton
public final class WebServiceStateAPI extends LiveModel {

	/**
	 * Public constructor
	 */
	@Inject
	public WebServiceStateAPI(){
		L.DI(this);
	}


	private int executingRequests;

	public class WebServiceStartedRequests extends Event {}
	public class WebServiceFinishedRequests extends Event{}


	
	public final synchronized void onRequestExecuted() {

		if( executingRequests == 0)
		{
			post(new WebServiceStartedRequests());
		}

		executingRequests++;

	}
	
	public final synchronized void onRequestResponse() {

		executingRequests--;
		
		if( executingRequests == 0)
		{
			post(new WebServiceFinishedRequests());
		}
	}
	
}
