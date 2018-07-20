package com.lfyt.mobile.android.frameworkmvp.models.util.view;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.lfyt.mobile.android.frameworkmvp.R;

import javax.inject.Inject;

public class AnimationAPI {


    @Inject
    public AnimationAPI(){
    }

    ///////////////////////////////////////////////////////////////////////////
    // Activity Transaction Animations
    ///////////////////////////////////////////////////////////////////////////
    public enum ActivityAnimations{
        DEFAULT, STATIC, FADE,
        RIGHT_IN_LEFT_OUT,
        LEFT_IN_FADE_OUT,
        LEFT_IN_RIGHT_OUT
        //TODO: CREATE MORE ANIMATIONS FOR ACTIVITY
    }



    public void animateActivityTransaction(Activity caller, ActivityAnimations animation)
    {
        switch (animation)
        {
            case STATIC:
                caller.overridePendingTransition(R.anim.no_anim, R.anim.no_anim);
                break;
            case FADE:
                caller.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case RIGHT_IN_LEFT_OUT:
                caller.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case LEFT_IN_FADE_OUT:
                caller.overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out);
                break;
            case LEFT_IN_RIGHT_OUT:
                caller.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

    }


    ///////////////////////////////////////////////////////////////////////////
    // View Objects Animation
    ///////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////////////////////////
    // Alpha
    ///////////////////////////////////////////////////////////////////////////

    public void showAlpha(final View view){
        view.animate().alpha(1);
    }

    public void showAlpha(final View view, boolean setVisibleBefore){
        view.animate().alpha(1).withStartAction(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showAlpha(View view, int duration){
        view.animate().setDuration(duration).alpha(1);
    }



    public void hideAlpha(View view){
        view.animate().alpha(0);
    }

    public void hideAlpha(final View view, boolean setGoneAfter){
        view.animate().alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        });
    }
    public void hideAlpha(final View view, int duration){
        view.animate().setDuration(duration).alpha(0);

    }

    ///////////////////////////////////////////////////////////////////////////
    // Slide
    ///////////////////////////////////////////////////////////////////////////

    public void slideInBottom(View view){
        view.animate().translationY(0);
    }

    public void slideOutBottom(View view){
        view.animate().translationY(view.getHeight());
    }

    public void slideInRight(View view){

        view.setTranslationX(view.getHeight());

        view.animate().translationX(0)
                .alpha(1)
                .setDuration(500)
        ;
    }

    public void slideOutLeft(View view){
        view.animate().translationX(-view.getHeight())
                .alpha(0)
                .setDuration(500)
        ;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Text
    ///////////////////////////////////////////////////////////////////////////

    public void blinkToChangeText(final TextView view, final String message, final int duration) {
        view.animate().setDuration(duration/2).alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                try{
                    view.setText(message);
                    view.animate().setDuration(duration/2).alpha(1);
                }catch (Exception e){

                }
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////
    // Rotating
    ///////////////////////////////////////////////////////////////////////////

    public void startRotation(View view, int rotateRes) {
        Animation startRotateAnimation = AnimationUtils.loadAnimation(view.getContext(), rotateRes);
        view.startAnimation(startRotateAnimation);
    }

}
