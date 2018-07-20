package com.lfyt.mobile.android.frameworkmvp.models.util;

import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.Locale;

public class PhoneAPI {

    PhoneAPI(){
    }

    private TelephonyManager mTelephonyManager;

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getOperationalSystem() {
        return "Android "+Build.VERSION.SDK_INT;
    }

    public String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public String getLanguage() {
        return Locale.getDefault().getISO3Language();
    }

    public String getOperator() {
        String op = mTelephonyManager.getNetworkOperatorName();
        if( op == null) op = mTelephonyManager.getSimOperatorName();
        return op;
    }
}
