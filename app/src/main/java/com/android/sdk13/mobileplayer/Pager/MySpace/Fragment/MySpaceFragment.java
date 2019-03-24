package com.android.sdk13.mobileplayer.Pager.MySpace.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class MySpaceFragment extends Fragment {

    Context mContext;
    ArrayList<String> data;
    String title;

    public MySpaceFragment(Context mContext, String title) {
        this.mContext = mContext;
        //this.data = data;
        this.title = title;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView( mContext );
        view.setText( "页面"+ title );
        view.setGravity( Gravity.CENTER );
        view.setTextSize( 15 );
        view.setTextColor( Color.RED );
        return view;
    }

    public String getTitle(){
        return title;
    }

}
