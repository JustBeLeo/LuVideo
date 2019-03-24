package com.android.sdk13.mobileplayer.Pager.NetVideo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sdk13.mobileplayer.Base.BasePager;
import com.android.sdk13.mobileplayer.R;
import com.android.sdk13.mobileplayer.Utils.LogUtil;
import com.cjj.MaterialRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import com.alibaba.fastjson.*;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class NetVideoPager extends BasePager {

    ArrayList<MovieInfo> MovieInfoList;
    @BindView(R.id.rv_netvideo)
    RecyclerView rv;
    @BindView(R.id.mrl_netvideo)
    MaterialRefreshLayout mrl;
    String data;
    Unbinder unbinder;
    NetVideoRecycleAdapter adapter;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            adapter = new NetVideoRecycleAdapter( mContext,MovieInfoList );
            rv.setAdapter( adapter );
            rv.setLayoutManager( new LinearLayoutManager( mContext,LinearLayoutManager.VERTICAL,false ) );
        }
    };


    public NetVideoPager(Context mContext) {
        super( mContext );
    }

    @Override
    public View initView() {
        View view = View.inflate( mContext, R.layout.pager_netvideo, null );
        unbinder = ButterKnife.bind( this, view );
        initData();
        return view;
    }

    @Override
    public void initData() {
        MovieInfoList = new ArrayList<>();
        OkGo.<String>get("http://api.m.mtime.cn/PageSubArea/TrailerList.api")
                .execute( new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        data = response.body();
                        data = data.substring( 12,data.length()-3 );
                        MovieInfoList = (ArrayList<MovieInfo>) JSON.parseArray( data,MovieInfo.class );
                        //当数据获取成功时开始挂适配器
                        handler.sendEmptyMessage( 1 );
                    }
                } );
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
