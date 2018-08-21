package com.lfyt.mobile.android.frameworkmvp.archtecture.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentView extends Fragment {
	
	private ViewMVP viewMVP;
	
	@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		viewMVP = new ViewMVP();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	    return viewMVP.setFragmentView(this, container, inflater);
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
	
	
	public boolean onBackPressed(){
        return false;
    }

}
