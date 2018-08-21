package com.lfyt.mobile.android.frameworkmvp.archtecture;


import com.lfyt.mobile.android.frameworkmvp.archtecture.service.BackgroundService;
import com.lfyt.mobile.android.log.Logger;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.model.Model;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter.PresenterContract;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.ViewContract;

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
		if( caller instanceof ViewContract){
			return "View";
		}


		//PRESENTER
		if( caller instanceof PresenterContract){
			return "Presenter";
		}


		//MODEL
		if( caller instanceof Model){
			return "Model";
		}


		//SERVICE
		if( caller instanceof BackgroundService){
			return "SERVICE";
		}

		return "Application";
	}







}
