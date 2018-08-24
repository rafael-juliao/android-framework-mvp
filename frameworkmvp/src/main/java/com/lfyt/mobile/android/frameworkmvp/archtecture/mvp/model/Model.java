package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.model;

import android.support.annotation.CallSuper;

import com.lfyt.mobile.android.livemodel.LiveModel;
import com.lfyt.mobile.android.livemodel.LiveModelAPI;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;


/**
 * Model is an extension of @{@link LiveModel},
 * that can:
 * 		*post event to subscribers
 * 		*inject its dependencies and subscribe to it
 */
public abstract class Model extends LiveModel {


	protected Model(){
		L.DI(this);
	}

	@CallSuper
	protected void setupModel() {
		subscribeInLiveModelSubscritionList(setupSubscriptionList());
	}

	protected abstract LiveModelAPI[] setupSubscriptionList();

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
