package com.android.sdk13.mobileplayer.Pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.sdk13.mobileplayer.Base.BasePager;
import com.android.sdk13.mobileplayer.Utils.LogUtil;

@SuppressLint("ValidFragment")
public class AudioPager extends BasePager {

    public AudioPager(Context mContext) {
        super( mContext );
    }

    @Override
    public View initView() {
        LogUtil.e("本地音频页面被初始化了");
        TextView view = new TextView( mContext );
        view.setText( "本地音频页面" );
        view.setGravity( Gravity.CENTER );
        view.setTextSize( 15 );
        view.setTextColor( Color.RED );
        return view;
    }

    @Override
    public void initData() {
        LogUtil.e("本地音频数据被初始化了");
    }
}
