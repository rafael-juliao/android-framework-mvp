package com.lfyt.mobile.android.frameworkmvp.models.util.view;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import javax.inject.Inject;

public class ViewUtilAPI {

    private DisplayMetrics mDisplayMetrics;

    @Inject
    public ViewUtilAPI(Application application) {
        this.mDisplayMetrics = application.getResources().getDisplayMetrics();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Density Pixels to Pixels
    ///////////////////////////////////////////////////////////////////////////
    public int DP_PX(int DP){
        return
                (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        DP,
                        mDisplayMetrics
                );
    }


    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

}
