package com.lfyt.mobile.android.frameworkmvp.archtecture;


import com.lfyt.mobile.android.log.Logger;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.model.LiveModelMVP;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter.BasePresenter;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.BaseView;
import com.lfyt.mobile.android.frameworkmvp.archtecture.service.BackgroundServiceMVP;

public class L extends Logger {


    public L(){}



	public static void init(String baseAppTag, boolean log)
	{
		Logger.setup(baseAppTag, new LoggerInterface() {
			@Override
			public String tag(Object o) {
				return TAG(o);
			}
		});
	}




	private static String TAG(Object caller) {

		//VIEW
		if( caller instanceof BaseView ){
			return "View";
		}


		//PRESENTER
		if( caller instanceof BasePresenter){
			return "Presenter";
		}


		//MODEL
		if( caller instanceof LiveModelMVP){
			return "Model";
		}


		//SERVICE
		if( caller instanceof BackgroundServiceMVP){
			return "SERVICE";
		}

		return "Application";
	}







}
