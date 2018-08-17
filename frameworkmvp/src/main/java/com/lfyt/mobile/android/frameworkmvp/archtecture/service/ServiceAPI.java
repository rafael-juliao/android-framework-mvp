package com.lfyt.mobile.android.frameworkmvp.archtecture.service;

import android.content.Context;
import android.content.Intent;

import com.lfyt.mobile.android.livemodel.Event;
import com.lfyt.mobile.android.livemodel.LiveModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServiceAPI extends LiveModel {

    private Context mContext;

    @Inject
    public ServiceAPI(Context context){
        mContext = context;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Start Background Service
    ///////////////////////////////////////////////////////////////////////////

    public void start(  Class<?> cls ){
        mContext.startService( new Intent(mContext, cls));
    }


    ///////////////////////////////////////////////////////////////////////////
    // Stop Background Service
    ///////////////////////////////////////////////////////////////////////////
    public void stop(  Class<?> cls ){
        post( new StopService(cls.getSimpleName()) );
    }


    public class StopService extends Event {

        private final String simpleName;

        public StopService(String simpleName) {
            this.simpleName = simpleName;
        }

        public boolean equals(Object object){
            return object.getClass().getSimpleName().equals(simpleName);
        }
    }

}
