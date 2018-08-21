package com.lfyt.mobile.android.frameworkmvp.archtecture.application;

import com.lfyt.mobile.android.livemodel.Event;
import com.lfyt.mobile.android.livemodel.LiveModel;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;


public final class ApplicationLifecycleAPI extends LiveModel {
	
	
	private final ActivityLifecycleAPI activityLifecycleAPI;
	
	
	//Events
	public class ApplicationStarted extends Event {}
	public class ApplicationStopped extends Event {}
	

	public ApplicationLifecycleAPI(ActivityLifecycleAPI activityLifecycleAPI) {
		L.DI(this);
		
		this.activityLifecycleAPI = activityLifecycleAPI;
		
		activityLifecycleAPI.subscribe(this);


		L.SPACE();
		L.D(this, "##############################################");
		L.D(this, "######### MVP APPLICATION CREATED ############");
		L.D(this, "##############################################");
		L.SPACE();
		
		
		
	}
	
	public boolean isStopped() {
		return activityLifecycleAPI.getStartedActivities() == 0;
	}
	
	
	@Subscribe
	public void onActivityStartedEvent(ActivityLifecycleAPI.ActivityStartedEvent event){
	
		if( activityLifecycleAPI.getStartedActivities() == 1){
			
			L.SPACE();
			L.D(this, "##############################################");
			L.D(this, "############# APPLICATION STARTED ############");
			L.D(this, "##############################################");
			L.SPACE();
			
			post(new ApplicationStarted());
		}
	}
	
	
	
	@Subscribe
	public void onActivityStoppedEvent (ActivityLifecycleAPI.ActivityStoppedEvent event){
		
		if( activityLifecycleAPI.getStartedActivities() == 0 ){
			
			L.SPACE();
			L.D(this, "##############################################");
			L.D(this, "############# APPLICATION STOPPED ############");
			L.D(this, "##############################################");
			L.SPACE();
			
			post(new ApplicationStopped());
		}
			
		
	}
	
	
	@Subscribe
	public void onActivityDestroyedEvent(ActivityLifecycleAPI.ActivityDestroyedEvent event){
		
		if( activityLifecycleAPI.getRunningActivities() == 0 ){
			
			L.SPACE();
			L.D(this, "##############################################");
			L.D(this, "######### ALL ACTIVITIES DESTROYED ###########");
			L.D(this, "##############################################");
			L.SPACE();
			
			System.gc();
		}
		
	}
	
	
	
	
}
