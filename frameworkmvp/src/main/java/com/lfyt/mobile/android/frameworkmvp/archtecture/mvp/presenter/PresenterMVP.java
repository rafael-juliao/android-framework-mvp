package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter;

import com.lfyt.mobile.android.livemodel.LiveModelAPI;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.BaseView;

public abstract class PresenterMVP<T extends BaseView> implements BasePresenter {

    public PresenterMVP(){
        L.DI(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // View Reference
    ///////////////////////////////////////////////////////////////////////////
    public T view;

    public void setView(T view) {
        this.view = view;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Live Model Subscription List
    ///////////////////////////////////////////////////////////////////////////

    public LiveModelAPI[] liveModelSubscriptionList;


    public abstract void setupSubscriptionList();

    protected final void subscriptionList(LiveModelAPI... liveModelSubscriptionList) {
        this.liveModelSubscriptionList = liveModelSubscriptionList;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Helper Methods
    ///////////////////////////////////////////////////////////////////////////
    public final void executeSub(){
        if( liveModelSubscriptionList != null){
            for( LiveModelAPI liveModel : liveModelSubscriptionList){
                liveModel.subscribe(this);
            }
        }
    }
    public final void executeUnsub(){
        if( liveModelSubscriptionList != null){
            for( LiveModelAPI liveModel : liveModelSubscriptionList){
                liveModel.unsubscribe(this);
            }
        }
    }
}
