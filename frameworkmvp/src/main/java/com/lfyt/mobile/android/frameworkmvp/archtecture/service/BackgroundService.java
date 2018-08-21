package com.lfyt.mobile.android.frameworkmvp.archtecture.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public abstract class BackgroundService extends Service {


    ///////////////////////////////////////////////////////////////////////////
    // The LiveModel that is connected to this interface
    ///////////////////////////////////////////////////////////////////////////

    private ServiceInterface ServiceInterfaceMVP;


    ///////////////////////////////////////////////////////////////////////////
    // Dependencies
    ///////////////////////////////////////////////////////////////////////////

    protected abstract ServiceInterface inject(Object component);

    @Inject ServiceAPI serviceAPI;





    ///////////////////////////////////////////////////////////////////////////
    // Interface
    ///////////////////////////////////////////////////////////////////////////


    @CallSuper
    protected void onServiceCreated(){
        L.D(this, "CREATED");
    }


    @CallSuper
    protected void onServiceStarted(){
        L.D(this, "STARTED");

        //Subscribe for Stop Service Event
        serviceAPI.subscribe(codeStopServiceEvent);

        ServiceInterfaceMVP.onStart();
    }



    @CallSuper
    protected void onServiceStopped(){
        L.D(this, "STOPPED");

        //Unsubscribe from Stop Service Event
        serviceAPI.unsubscribe(codeStopServiceEvent);

        ServiceInterfaceMVP.onStop();

        stopSelf();
        L.D(this, "DESTROYED");
    }








    ///////////////////////////////////////////////////////////////////////////
    // Stop Service Code
    ///////////////////////////////////////////////////////////////////////////

    private Object codeStopServiceEvent = new Object(){

        @Subscribe
        public void onStopService(ServiceAPI.StopService event){
            onStopEvent(event);
        }

    };

    private void onStopEvent(ServiceAPI.StopService event) {
        if( event.equals(this) ){
            onServiceStopped();
        }
    }














    ///////////////////////////////////////////////////////////////////////////
    // Service Lifecycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    @CallSuper
    public void onCreate() {
        //Inject Dependencies
        ServiceInterfaceMVP = inject( ((ApplicationFrameworkMVP) getApplication()).getApplicationComponent() );

        onServiceCreated();
    }


    @Override
    @CallSuper
    public int onStartCommand(Intent intent, int flags, int startId) {
        onServiceStarted();
        return START_STICKY_COMPATIBILITY;
    }


    // Service Binding
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.D(this, "BINDED");
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        L.D(this, "REBIND");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.D(this, "UNBIND");
        return true; // TRUE ALLOW REBIND
    }






}
