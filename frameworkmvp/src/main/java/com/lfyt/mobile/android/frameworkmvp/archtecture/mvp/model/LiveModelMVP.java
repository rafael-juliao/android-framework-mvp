package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.model;

import android.app.Application;

import com.lfyt.mobile.android.livemodel.LiveModel;
import com.lfyt.mobile.android.livemodel.LiveModelAPI;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;


/**
 * Live Model MVP is an extension of @{@link LiveModel},
 * that can:
 * 		*post event to subscribers
 * 		*inject its dependencies and subscribe to it
 */
public abstract class LiveModelMVP extends LiveModel {



	protected LiveModelMVP(Application application){

		inject( ((ApplicationFrameworkMVP) application) );

		L.DI(this);

		setupSubscriptionList();
	}


	///////////////////////////////////////////////////////////////////////////
	// Dependency Injection
	///////////////////////////////////////////////////////////////////////////

	protected abstract void inject(ApplicationFrameworkMVP component);




	///////////////////////////////////////////////////////////////////////////
	// Live Model Subscription List
	///////////////////////////////////////////////////////////////////////////

	protected abstract void setupSubscriptionList();

	protected final void subscriptionList(LiveModelAPI... liveModelSubscriptionList) {
		subscribeInLiveModelSubscritionList(liveModelSubscriptionList);
	}

	private void subscribeInLiveModelSubscritionList(LiveModelAPI... liveModelSubscriptionList) {

		if( liveModelSubscriptionList != null ){

			L.D(this, "~~>SUBSCRIBING<~~ Live Model List ==> %s" );

			for( LiveModelAPI liveModel : liveModelSubscriptionList)
			{
				liveModel.subscribe(this);
			}
		}
	}


}
