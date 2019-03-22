package com.android.sdk13.mobileplayer.Pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.sdk13.mobileplayer.Base.BasePager;
import com.android.sdk13.mobileplayer.Pager.Adapter.MySpacePagerAdpter;
import com.android.sdk13.mobileplayer.Pager.Fragment.MySpaceFragment;
import com.android.sdk13.mobileplayer.R;
import com.android.sdk13.mobileplayer.Utils.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class MySpacePager extends BasePager {

    @BindView(R.id.tl_myspace)
    TabLayout tl;
    @BindView(R.id.vp_myspace)
    ViewPager vp;

    public MySpacePager(Context mContext) {
        super( mContext );
    }

    @Override
    public View initView() {
        View view = View.inflate( mContext, R.layout.fragment_myspace, null );
        ButterKnife.bind( this, view );
        ArrayList<MySpaceFragment> list = new ArrayList<>();
        for(int i = 0;i<10;i++)
            list.add( new MySpaceFragment( mContext,"页面"+i ) );
        if(!isAdded()){
            return null;
        }
        MySpacePagerAdpter adpter = new MySpacePagerAdpter( getChildFragmentManager(),list );
        vp.setAdapter( adpter );
        tl.setTabMode( TabLayout.MODE_SCROLLABLE );
        tl.setupWithViewPager( vp );
        return view;
    }

    @Override
    public void initData() {
        LogUtil.e( "我的空间数据被初始化了" );
    }
}
