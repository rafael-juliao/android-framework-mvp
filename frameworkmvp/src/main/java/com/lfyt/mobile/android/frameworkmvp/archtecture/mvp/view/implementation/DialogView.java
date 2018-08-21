package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.implementation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view.ViewMVP;

/**
 * Created by rafaeljuliao on 03/04/17.
 */

public abstract class DialogView extends AppCompatDialogFragment {
	
	
	private ViewMVP viewMVP;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewMVP = new ViewMVP();
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return viewMVP.setDialogView(this);
	}



	@Override
	public void onStart() {
		viewMVP.subscribe();
		super.onStart();
		viewMVP.start();
	}


	@Override
	public void onStop() {
		viewMVP.stop();
		super.onStop();
		viewMVP.unsubscribe();
	}

	///////////////////////////////////////////////////////////////////////////
	// Default UI Dialog Options
	///////////////////////////////////////////////////////////////////////////
	
	
	public MaterialDialog createDialog(MaterialDialog.Builder builder) {
		
		if( getTitle() != -1 )
			builder.title(getTitle());
		
		if( getPositiveButton() != -1)
			builder.positiveText(getPositiveButton());
		else
			builder.positiveText("OK");
		
		builder.onPositive(onClickPositive);
		
		
		if( getNegativeButton() != -1 ){
			builder
					.negativeText(getNegativeButton())
					.onNegative(onClickNegative);
		}
		
		if( getCancelable() ){
			builder
					.autoDismiss(false)
					.cancelable(true)
					.canceledOnTouchOutside(true);
		}
		
		return builder.build();
		
	}
	
	
	private MaterialDialog.SingleButtonCallback onClickPositive = new MaterialDialog.SingleButtonCallback() {
		@Override
		public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
			onPositive();
		}
	};
	
	
	private MaterialDialog.SingleButtonCallback onClickNegative = new MaterialDialog.SingleButtonCallback() {
		@Override
		public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
			onNegative();
		}
	};
	
	
	
	protected int getTitle() {
		return -1;
	}
	
	protected abstract int getLayout();
	
	protected void setupView() { }
	
	
	protected boolean getCancelable() {
		return false;
	}
	
	
	
	protected int getPositiveButton() {
		return -1;
	}
	
	protected void onPositive(){ }
	
	
	
	
	
	protected int getNegativeButton() {
		return -1;
	}
	
	protected void onNegative(){ }
	
	
}
