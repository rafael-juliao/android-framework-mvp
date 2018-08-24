package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lfyt.mobile.android.frameworkmvp.archtecture.L;
import com.lfyt.mobile.android.frameworkmvp.archtecture.application.ApplicationFrameworkMVP;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.presenter.Presenter;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.implementation.ActivityView;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.implementation.DialogView;
import com.lfyt.mobile.android.livemodel.SubscriptionList;

import butterknife.ButterKnife;


public class ViewMVP {
	
	
	///////////////////////////////////////////////////////////////////////////
	// View Reference
	///////////////////////////////////////////////////////////////////////////
	private ViewContract mBaseView;
	
	
	///////////////////////////////////////////////////////////////////////////
	// Presenter Reference
	///////////////////////////////////////////////////////////////////////////
	private Presenter mPresenterMVP;
	
	private void setupEmptyPresenter(){
		mPresenterMVP = new Presenter(){
			@Override
			public void start() { }

			@Override
			public void stop() { }

			@Override
			protected void setupSubscriptionList(SubscriptionList subscriptionList) { }
		};
	}


	///////////////////////////////////////////////////////////////////////////
	// SETUP MAIN METHOD
	///////////////////////////////////////////////////////////////////////////


	private void setupPresenterView(ViewContract baseView, ApplicationFrameworkMVP applicationFrameworkMVP) {
		mBaseView = baseView;

		mPresenterMVP = mBaseView.inject(applicationFrameworkMVP);
		if( mPresenterMVP == null) setupEmptyPresenter();

		mPresenterMVP.setView(mBaseView);
		mPresenterMVP.setupSubscription();

	}



	///////////////////////////////////////////////////////////////////////////
	// Setup Activity View
	///////////////////////////////////////////////////////////////////////////
	
	public void setActivityView(ActivityView activityViewMVP){
		setupPresenterView(
				(ViewContract) activityViewMVP,
				((ApplicationFrameworkMVP) activityViewMVP.getApplication()));

		activityViewMVP.setContentView(mBaseView.getLayout());
		ButterKnife.bind(activityViewMVP);
		mBaseView.setupView();
		
	}


	///////////////////////////////////////////////////////////////////////////
	// Setup Fragment View
	///////////////////////////////////////////////////////////////////////////

	public View setFragmentView(Fragment fragmentView, ViewGroup container, LayoutInflater inflater){
		setupPresenterView(
				(ViewContract) fragmentView,
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

	public MaterialDialog setDialogView(DialogView dialogView){

		setupPresenterView(
				(ViewContract) dialogView,
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

	public void subscribe(){
		mPresenterMVP.subscribe();
	}

	public void start(){
		mPresenterMVP.start();
		L.D(mPresenterMVP, "STARTED");
	}

	public void stop() {
		mPresenterMVP.stop();
		L.D(mPresenterMVP, "STOPPED");
	}

	public void unsubscribe(){
		mPresenterMVP.unsubscribe();
	}
	
	
}
