package com.lfyt.mobile.android.frameworkmvp.archtecture.webservice;

import android.support.annotation.CallSuper;

import com.lfyt.mobile.android.webservice.WebServiceModel;
import com.lfyt.mobile.android.webservice.WebServiceResponse;
import com.lfyt.mobile.android.frameworkmvp.models.framework.webservice.WebServiceStateAPI;

import retrofit2.Call;
import retrofit2.Response;

public class WebServiceModelMVP<T extends WebServiceResponse> extends WebServiceModel<T> {

    private WebServiceStateAPI webServiceStateAPI;

    public WebServiceModelMVP(WebServiceStateAPI webServiceStateAPI){
        this.webServiceStateAPI = webServiceStateAPI;
    }

    @Override
    @CallSuper
    protected void executeRequest(Call<T> call) {

        if( handleResponse ){
            webServiceStateAPI.onRequestExecuted();
        }

        super.executeRequest(call);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        super.onResponse(call, response);

        if( handleResponse ){
            webServiceStateAPI.onRequestResponse();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable error) {
        super.onFailure(call, error);

        if( handleResponse ){
            webServiceStateAPI.onRequestResponse();
        }
    }
}
