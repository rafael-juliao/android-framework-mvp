package com.lfyt.mobile.android.frameworkmvp.archtecture.application;

import android.app.Application;
import android.content.Context;

import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.service.ServiceAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FrameworkMVPModule {

	public FrameworkMVPModule(Application application){
		L.DI(this);
		this.application = application;
	}



	///////////////////////////////////////////////////////////////////////////
	// Application Context
	///////////////////////////////////////////////////////////////////////////

	private final Application application;

	@Provides
	Application provideApplication(){
		return application;
	}

	@Provides
	Context provideContext(){
		return application;
	}


	///////////////////////////////////////////////////////////////////////////
	// Lifecycle Models
	///////////////////////////////////////////////////////////////////////////

	@Provides
	@Singleton
	ApplicationLifecycleAPI applicationLifecycleAPI(ActivityLifecycleAPI activityLifecycleAPI){
		return new ApplicationLifecycleAPI(activityLifecycleAPI);
	}

	@Provides
	@Singleton
	ActivityLifecycleAPI activityLifecycleAPI(Application application){
		return new ActivityLifecycleAPI(application);
	}


	///////////////////////////////////////////////////////////////////////////
	// Service API
	///////////////////////////////////////////////////////////////////////////

	@Provides
	@Singleton
	ServiceAPI serviceAPI(Context context){
		return new ServiceAPI(context);
	}
}
