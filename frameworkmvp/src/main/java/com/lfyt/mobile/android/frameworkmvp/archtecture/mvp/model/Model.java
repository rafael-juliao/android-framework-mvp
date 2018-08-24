package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.model;

import android.support.annotation.CallSuper;

import com.lfyt.mobile.android.livemodel.LiveModel;
import com.lfyt.mobile.android.livemodel.LiveModelAPI;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;
import com.lfyt.mobile.android.livemodel.SubscriptionList;


/**
 * Model is an extension of @{@link LiveModel},
 * that can:
 * 		*post event to subscribers
 * 		*inject its dependencies and subscribe to it
 */
public abstract class Model extends LiveModel {


	private final SubscriptionList subscriptionList;

	protected Model(){
		L.DI(this);
		subscriptionList = new SubscriptionList();
	}

	@CallSuper
	protected void setupModel() {
		setupSubscriptionList(subscriptionList);

		if( subscriptionList.size() == 0 ) return;

		L.D(this, "~~>SUBSCRIBING<~~ Live Model List ==> %s" );
		subscriptionList.subscribe(this);

	}

	protected abstract void setupSubscriptionList(SubscriptionList subscriptionList);



}
