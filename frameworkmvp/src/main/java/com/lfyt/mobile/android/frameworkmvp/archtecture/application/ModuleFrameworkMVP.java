package com.lfyt.mobile.android.frameworkmvp.archtecture.application;

import android.app.Application;
import android.content.Context;

import com.lfyt.mobile.android.frameworkmvp.archtecture.L;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleFrameworkMVP {
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// APPLICATION
	///////////////////////////////////////////////////////////////////////////


	public ModuleFrameworkMVP(Application application){
		L.DI(this);
		this.application = application;
	}


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
	// MVP
	///////////////////////////////////////////////////////////////////////////

	/*
	@Provides
	@Singleton
	DataStateAPI provideDataStateAPI(ApplicationStateAPI applicationAPI, WebServiceStateAPI restAPIManager){
		return new DataStateAPI(applicationAPI, restAPIManager);
	}

	*/




	///////////////////////////////////////////////////////////////////////////
	// Service
	///////////////////////////////////////////////////////////////////////////

	/*
	@Provides
	@Singleton
    BackgroundServiceManager provideBackgroundServiceManager(Application application){
		return new BackgroundServiceManager(application);
	}


	*/



	///////////////////////////////////////////////////////////////////////////
	// Web Service
	///////////////////////////////////////////////////////////////////////////

	/*
	@Provides
	@Singleton
	WebServiceStateAPI provideWebServiceStateAPI(){
		return new WebServiceStateAPI();
	}
	
	
	*/
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// Android
	///////////////////////////////////////////////////////////////////////////

	/*
	@Provides
	@Singleton
	ApplicationStateAPI provideApplicationStateAPI(ActivityLifecycleAPI activityLifecycleAPI){
		return new ApplicationStateAPI(activityLifecycleAPI);
	}



	@Provides
	@Singleton
	PermissionAPI providePermissionAPI(ActivityLifecycleAPI activityLifecycleAPI){
		return new PermissionAPI(application, activityLifecycleAPI);
	}
	
	
	@Provides
	@Singleton
	ActivityAPI provideActivityAPI(){
		return new ActivityAPI();
	}

	@Provides
	@Singleton
	ActivityLifecycleAPI provideActivityLifecycleAPI(Application application){
		return new ActivityLifecycleAPI(application);
	}

	@Provides
	StringAPI provideStringAPI(Application application){
		return new StringAPI(application);
	}

	@Provides
	VersionAPI provideVersionAPI(){
		return new VersionAPI();
	}

	*/






}
