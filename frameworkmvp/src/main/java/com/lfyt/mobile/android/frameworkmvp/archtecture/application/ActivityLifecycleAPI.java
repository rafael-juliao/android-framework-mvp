package com.lfyt.mobile.android.frameworkmvp.archtecture.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.lfyt.mobile.android.livemodel.Event;
import com.lfyt.mobile.android.livemodel.LiveModel;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ActivityLifecycleAPI extends LiveModel implements Application.ActivityLifecycleCallbacks{
	

	@Inject
	public ActivityLifecycleAPI(Application application){
		L.DI(this);
		application.registerActivityLifecycleCallbacks(this);
	}

	//Activity Lifecycle Events
	public class ActivityCreatedEvent extends Event {}
	public class ActivityStartedEvent extends Event{}
	public class ActivityResumedEvent extends Event{}
	public class ActivityPausedEvent extends Event{}
	public class ActivityStoppedEvent extends Event{}
	public class ActivityDestroyedEvent extends Event{}
	

	//Current Activity
	private Activity currentActivity;
	public Activity getCurrentActivity() { return currentActivity; }

	//Current Activity State
	private int runningActivities = 0;
	private int startedActivities = 0;
	private int resumedActivities = 0;

	//Get Current Activity State
	public int getRunningActivities() { return runningActivities;  }
	public int getStartedActivities() { return startedActivities; }
	public int getResumedActivities() { return resumedActivities;	}




	///////////////////////////////////////////////////////////////////////////
	// Acvities Lifecycle
	///////////////////////////////////////////////////////////////////////////

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		runningActivities++;
		post(new ActivityCreatedEvent());
		L.D(this, "%s ==> CREATED", activity.getClass().getSimpleName());
	}
	
	@Override
	public void onActivityStarted(Activity activity) {
		currentActivity = activity;
		startedActivities++;
		post(new ActivityStartedEvent());
		L.D(this, "%s ==> STARTED", activity.getClass().getSimpleName());
	}
	
	@Override
	public void onActivityResumed(Activity activity) {
		resumedActivities++;
		post(new ActivityResumedEvent());
		L.D(this, "%s ==> RESUMED", activity.getClass().getSimpleName());
	}
	
	@Override
	public void onActivityPaused(Activity activity) {
		resumedActivities--;
		post(new ActivityPausedEvent());
		L.D(this, "%s ==> PAUSED", activity.getClass().getSimpleName());
	}
	
	@Override
	public void onActivityStopped(Activity activity) {
		startedActivities--;
		post(new ActivityStoppedEvent());
		L.D(this, "%s ==> STOPPED", activity.getClass().getSimpleName());
		
		if( startedActivities == 0)
			currentActivity = null;
	}
	
	@Override
	public void onActivityDestroyed(Activity activity) {
		runningActivities--;
		post(new ActivityDestroyedEvent());
		L.D(this, "%s ==> DESTROYED", activity.getClass().getSimpleName());
	}
	
	
	
	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
		L.D(this, "%s ==> SAVE INSTANCE", activity.getClass().getSimpleName());
	}

}
