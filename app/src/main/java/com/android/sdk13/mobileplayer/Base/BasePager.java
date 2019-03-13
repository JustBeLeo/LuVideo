package com.android.sdk13.mobileplayer.Base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasePager extends Fragment{

    public Context mContext;
    public View rootview;

    public BasePager(Context mContext) {
        this.mContext = mContext;
        rootview = initView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();
    public abstract void initData();
}
