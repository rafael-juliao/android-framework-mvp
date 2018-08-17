package com.lfyt.mobile.android.frameworkmvp.archtecture.application;

import android.app.Application;
import android.content.Context;

import com.lfyt.mobile.android.frameworkmvp.archtecture.L;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleFrameworkMVP {

	public ModuleFrameworkMVP(Application application){
		L.DI(this);
		this.application = application;
	}


	private final Application application;

	@Provides
	Application provideApplication(){
		return application;
	}

	@Provides
	Context provideContext(){
		return application;
	}

}
