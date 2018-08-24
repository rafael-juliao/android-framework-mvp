package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter;

import com.lfyt.mobile.android.livemodel.LiveModelAPI;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.ViewContract;
import com.lfyt.mobile.android.livemodel.SubscriptionList;

public abstract class Presenter<T extends ViewContract> implements PresenterContract {

    private SubscriptionList subscriptionList;

    public Presenter(){
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
    protected abstract void setupSubscriptionList(SubscriptionList subscriptionList);

    public final void setupSubscription() {
        if( subscriptionList != null) return;

        subscriptionList = new SubscriptionList();
        setupSubscriptionList(subscriptionList);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helper Methods
    ///////////////////////////////////////////////////////////////////////////
    public final void subscribe(){
        setupSubscription();
        subscriptionList.subscribe(this);
    }


    public final void unsubscribe(){
        subscriptionList.unsubscribe(this);
    }
}
