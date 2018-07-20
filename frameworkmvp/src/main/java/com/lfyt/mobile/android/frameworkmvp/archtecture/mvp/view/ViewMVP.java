package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter.PresenterMVP;

import butterknife.ButterKnife;


class ViewMVP {
	
	
	///////////////////////////////////////////////////////////////////////////
	// View Reference
	///////////////////////////////////////////////////////////////////////////
	private BaseView mBaseView;
	
	
	///////////////////////////////////////////////////////////////////////////
	// Presenter Reference
	///////////////////////////////////////////////////////////////////////////
	private PresenterMVP mPresenterMVP;
	
	private void setupEmptyPresenter(){
		mPresenterMVP = new PresenterMVP()
		{
			@Override public void setupSubscriptionList() { }
			@Override public void start() { }
			@Override public void stop() { }
		};
	}


	///////////////////////////////////////////////////////////////////////////
	// SETUP MAIN METHOD
	///////////////////////////////////////////////////////////////////////////


	private void setupPresenterView(BaseView baseView, ApplicationFrameworkMVP applicationFrameworkMVP) {
		mBaseView = baseView;

		mPresenterMVP = mBaseView.inject(applicationFrameworkMVP);
		if( mPresenterMVP == null) setupEmptyPresenter();

		mPresenterMVP.setView(mBaseView);
		mPresenterMVP.setupSubscriptionList();

	}



	///////////////////////////////////////////////////////////////////////////
	// Setup Activity View
	///////////////////////////////////////////////////////////////////////////
	
	void setActivityView(ActivityViewMVP activityViewMVP){
		setupPresenterView(
				(BaseView) activityViewMVP,
				((ApplicationFrameworkMVP) activityViewMVP.getApplication()));

		activityViewMVP.setContentView(mBaseView.getLayout());
		ButterKnife.bind(activityViewMVP);
		mBaseView.setupView();
		
	}


	///////////////////////////////////////////////////////////////////////////
	// Setup Fragment View
	///////////////////////////////////////////////////////////////////////////
	
	View setFragmentView(Fragment fragmentView, ViewGroup container, LayoutInflater inflater){
		setupPresenterView(
				(BaseView) fragmentView,
				((ApplicationFrameworkMVP) fragmentView.getActivity().getApplication())
		);

		View layoutView = inflater.inflate(mBaseView.getLayout(), container, false);
		ButterKnife.bind(fragmentView, layoutView);
		mBaseView.setupView();
		
		return layoutView;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// Setup Dialog View
	///////////////////////////////////////////////////////////////////////////
	
	MaterialDialog setDialogView(DialogViewMVP dialogView){

		setupPresenterView(
				(BaseView) dialogView,
				((ApplicationFrameworkMVP) dialogView.getActivity().getApplication())
		);

		MaterialDialog.Builder builder = new MaterialDialog.Builder(dialogView.getContext());
		builder.customView(mBaseView.getLayout(), false);
		MaterialDialog materialDialog = dialogView.createDialog(builder);
		ButterKnife.bind(this, materialDialog.getCustomView());
		mBaseView.setupView();
		
		return materialDialog;
	}
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// View Lifecycle
	///////////////////////////////////////////////////////////////////////////
	
	void subscribe(){
		mPresenterMVP.executeSub();
	}
	
	void start(){
		mPresenterMVP.start();
		L.D(mPresenterMVP, "STARTED");
	}
	
	void stop() {
		mPresenterMVP.stop();
		L.D(mPresenterMVP, "STOPPED");
	}

	void unsubscribe(){
		mPresenterMVP.executeUnsub();
	}
	
	
}
