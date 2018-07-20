package com.lfyt.mobile.android.frameworkmvp.models.util.view;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lfyt.mobile.android.frameworkmvp.R;
import com.lfyt.mobile.android.frameworkmvp.models.util.StringAPI;
import com.lfyt.mobile.android.frameworkmvp.models.util.VersionAPI;

import javax.inject.Inject;

public class ToastAPI {

	///////////////////////////////////////////////////////////////////////////
	// Dependencies
	///////////////////////////////////////////////////////////////////////////

	private final Application application;
	private final StringAPI str;


	@Inject
	public ToastAPI(Application application, ViewUtilAPI viewAPI, StringAPI str, VersionAPI versionAPI){
		this.application = application;
		this.str = str;
		setupToastView(viewAPI);
	}


	///////////////////////////////////////////////////////////////////////////
	// Setup Toast View
	///////////////////////////////////////////////////////////////////////////

	private void setupToastView(ViewUtilAPI viewAPI) {
		TOAST_PADDING_DP_VERTICAL = viewAPI.DP_PX(TOAST_PADDING_DP_VERTICAL);
		TOAST_PADDING_DP_HORIZONTAL = viewAPI.DP_PX(TOAST_PADDING_DP_HORIZONTAL);
		TOAST_PADDING_DP_DRAWABLE = viewAPI.DP_PX(TOAST_PADDING_DP_DRAWABLE);
		TOAST_DRAWABLE_ICON_SIZE = viewAPI.DP_PX(TOAST_DRAWABLE_ICON_SIZE);
	}

	//Setup Text
	protected int TOAST_TEXT_SIZE = 16;

	//Setup Layout
	private int TOAST_PADDING_DP_VERTICAL = 12;
	private int TOAST_PADDING_DP_HORIZONTAL = 16;

	//Setup Icon
	private int TOAST_PADDING_DP_DRAWABLE = 8;
	private int TOAST_DRAWABLE_ICON_SIZE = 24;


	//Setup Duration
	protected int TOAST_DEFAULT_DURATION = Toast.LENGTH_SHORT;



	//Setup Position
	public enum ToastPosition {
		BOTTOM, CENTER, TOP
	}

	protected ToastPosition TOAST_DEFAULT_POSITION = ToastPosition.BOTTOM;





	///////////////////////////////////////////////////////////////////////////
	// Show Toast Method
	///////////////////////////////////////////////////////////////////////////

	private void showToast(String message, int backgroundResource, int textColor, ToastPosition toastPosition, int duration, int icon) {

		//Setup Toast
		Toast toast = Toast.makeText(application, message, duration);

		//Setup Layout
		View toastView = toast.getView();

		toastView.setBackgroundResource(backgroundResource);
		toastView.setPadding(
				TOAST_PADDING_DP_HORIZONTAL,
				TOAST_PADDING_DP_VERTICAL,
				TOAST_PADDING_DP_HORIZONTAL,
				TOAST_PADDING_DP_VERTICAL);


		//Setup Position
		if( toastPosition == ToastPosition.CENTER){
			toast.setGravity(Gravity.CENTER, 0,0 );
		}

		if( toastPosition == ToastPosition.TOP ){
			toast.setGravity(Gravity.TOP, 0,0 );
		}

		//Setup Text
		TextView toastText = toastView.findViewById(android.R.id.message);
		toastText.setTextColor(ContextCompat.getColor(application, textColor));
		toastText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TOAST_TEXT_SIZE);

		//toastText.setTypeface(null, Typeface.BOLD); //only text style(only bold)

		//Setup Icon
		Drawable drawableCheck = application.getResources().getDrawable(icon);
		drawableCheck.setBounds( 0, 0, TOAST_DRAWABLE_ICON_SIZE, TOAST_DRAWABLE_ICON_SIZE);
		toastText.setCompoundDrawables(drawableCheck, null, null, null);
		toastText.setCompoundDrawablePadding(TOAST_PADDING_DP_DRAWABLE);


		//Show Text
		toast.show();
	}





	///////////////////////////////////////////////////////////////////////////
	// POSITIVE MESSAGES
	///////////////////////////////////////////////////////////////////////////

	private int TOAST_POSITIVE_LAYOUT = R.drawable.shape_toast_positive;

	//Using Resource Id
	public void showPositiveMessage(int id, int duration, ToastPosition toastPosition){
		showToast(str.id(id), TOAST_POSITIVE_LAYOUT, R.color.white, toastPosition, duration, R.drawable.ic_done_white_24dp);
	}

	public void showPositiveMessage(int id, int duration){
		showPositiveMessage(id, duration, TOAST_DEFAULT_POSITION);
	}
	public void showPositiveMessage(int id, ToastPosition toastPosition){
		showPositiveMessage(id, TOAST_DEFAULT_DURATION, toastPosition);
	}
	public void showPositiveMessage(int id){
		showPositiveMessage(id, TOAST_DEFAULT_DURATION, TOAST_DEFAULT_POSITION);
	}


	//Using String
	public void showPositiveMessage(String message, int duration, ToastPosition toastPosition){
		showToast(str.id(message), TOAST_POSITIVE_LAYOUT, R.color.white, toastPosition, duration, R.drawable.ic_done_white_24dp);
	}

	public void showPositiveMessage(String message, int duration){
		showPositiveMessage(message, duration, TOAST_DEFAULT_POSITION);
	}
	public void showPositiveMessage(String message, ToastPosition toastPosition){
		showPositiveMessage(message, TOAST_DEFAULT_DURATION, toastPosition);
	}
	public void showPositiveMessage(String message){
		showPositiveMessage(message, TOAST_DEFAULT_DURATION, TOAST_DEFAULT_POSITION);
	}







	///////////////////////////////////////////////////////////////////////////
	// ERROR MESSAGES
	///////////////////////////////////////////////////////////////////////////

	private int TOAST_ERROR_LAYOUT = R.drawable.shape_toast_error;


	//Using Resource Id
	public void showErrorMessage(int id, int duration, ToastPosition toastPosition){
		showToast(str.id(id), TOAST_ERROR_LAYOUT, R.color.white, toastPosition, duration, R.drawable.ic_clear_white_24dp);
	}
	public void showErrorMessage(int id, int duration){
		showErrorMessage(id, duration, TOAST_DEFAULT_POSITION);
	}
	public void showErrorMessage(int id, ToastPosition toastPosition){
		showErrorMessage(id, TOAST_DEFAULT_DURATION, toastPosition);
	}
	public void showErrorMessage(int id){
		showErrorMessage(id, TOAST_DEFAULT_DURATION, TOAST_DEFAULT_POSITION);
	}


	//Using String
	public void showErrorMessage(String message, int duration, ToastPosition toastPosition){
		showToast(message, TOAST_ERROR_LAYOUT, R.color.white, toastPosition, duration, R.drawable.ic_clear_white_24dp);
	}
	public void showErrorMessage(String message, int duration){
		showErrorMessage(message, duration, TOAST_DEFAULT_POSITION);
	}
	public void showErrorMessage(String message, ToastPosition toastPosition){
		showErrorMessage(message, TOAST_DEFAULT_DURATION, toastPosition);
	}
	public void showErrorMessage(String message){
		showErrorMessage(message, TOAST_DEFAULT_DURATION, TOAST_DEFAULT_POSITION);
	}


	///////////////////////////////////////////////////////////////////////////
	// INFO MESSAGES
	///////////////////////////////////////////////////////////////////////////

	private int TOAST_INFO_LAYOUT = R.drawable.shape_toast_info;


	//Using Resource Id
	public void showInfoMessage(int id, int duration, ToastPosition toastPosition){
		showToast(str.id(id), TOAST_INFO_LAYOUT, R.color.white, toastPosition, duration, R.drawable.ic_clear_white_24dp);
	}
	
	public void showInfoMessage(int id, int duration){
		showInfoMessage(id, duration, TOAST_DEFAULT_POSITION);
	}
	public void showInfoMessage(int id, ToastPosition toastPosition){
		showInfoMessage(id, TOAST_DEFAULT_DURATION, toastPosition);
	}
	public void showInfoMessage(int id){
		showInfoMessage(id, TOAST_DEFAULT_DURATION, TOAST_DEFAULT_POSITION);
	}


	//Using String
	public void showInfoMessage(String message, int duration, ToastPosition toastPosition){
		showToast(message, TOAST_INFO_LAYOUT, R.color.black, toastPosition, duration, R.drawable.ic_info_outline_black_24dp);
	}
	public void showInfoMessage(String message, int duration){
		showInfoMessage(message, duration, TOAST_DEFAULT_POSITION);
	}
	public void showInfoMessage(String message, ToastPosition toastPosition){
		showInfoMessage(message, TOAST_DEFAULT_DURATION, toastPosition);
	}
	public void showInfoMessage(String message){
		showInfoMessage(message, TOAST_DEFAULT_DURATION, TOAST_DEFAULT_POSITION);
	}






	//TODO: WARNING MESSAGES
}
