package com.android.sdk13.mobileplayer.Pager.Video;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sdk13.mobileplayer.Base.BasePager;
import com.android.sdk13.mobileplayer.Domain.MediaItem;
import com.android.sdk13.mobileplayer.Pager.Video.Activity.MyVideoPlayer;
import com.android.sdk13.mobileplayer.Pager.Video.Adapter.VideoPagerAdapter;
import com.android.sdk13.mobileplayer.R;
import com.android.sdk13.mobileplayer.Utils.LogUtil;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

@SuppressLint("ValidFragment")
public class VideoPager extends BasePager {

    @BindView(R.id.rv_video)
    RecyclerView rv_video;
    @BindView(R.id.tv_video_novideo)
    TextView noVideo;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView( R.id.mrl_video )
    MaterialRefreshLayout mrl_video;

    Unbinder unbinder;

    VideoPagerAdapter adapter = null;
    List<MediaItem> mediaItemList;
    EventBus bus;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            avi.hide();
            if(mediaItemList!=null&&mediaItemList.size()>0){
                noVideo.setVisibility( View.INVISIBLE );
                //有数据，设置适配器
                adapter = new VideoPagerAdapter( mContext,mediaItemList );
                adapter.setOnItemClickListener( new VideoPagerAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        EventBus.getDefault().postSticky(  mediaItemList.get( position ).getPath() );
                        /*// 直接启动一个全屏页面
                        JCFullScreenActivity.startActivity(mContext,
                                mediaItemList.get( position ).getPath(),
                                JCVideoPlayerStandard.class,
                                mediaItemList.get( position ).getName());
                        */
                        //新建一个显式意图，并将数据传入
                        Intent intent = new Intent(mContext, MyVideoPlayer.class );
                        startActivity( intent );
                    }
                } );
                rv_video.setAdapter( adapter );
                rv_video.setLayoutManager( new LinearLayoutManager( mContext,LinearLayoutManager.VERTICAL,true ) );
                rv_video.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
                rv_video.scrollToPosition( mediaItemList.size()-1 );
            }else{
                //没有数据
                noVideo.setVisibility( View.VISIBLE );
            }
        }
    };

    public VideoPager(final Context mContext) {
        super( mContext );
    }

    @Override
    public View initView() {
        LogUtil.e( "本地视频页面被初始化了" );
        View view = View.inflate( mContext, R.layout.pager_video, null );
        unbinder = ButterKnife.bind( this, view );
        initData();
        setSwipeRefresh();
        return view;
    }

    private void setSwipeRefresh() {
        mrl_video.setIsOverLay(false);
        mrl_video.setWaveShow(false);
        mrl_video.setMaterialRefreshListener( new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getDataFromSdCard();
                Toast.makeText(mContext,"刷新成功",Toast.LENGTH_SHORT).show();
                mrl_video.finishRefresh();
            }
        } );
    }

    @Override
    public void initData() {
        LogUtil.e( "本地视频数据被初始化了" );
        getDataFromSdCard();
    }

    // 从sd卡获取文件 通过ContentProvider
    private void getDataFromSdCard() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mediaItemList = new ArrayList<>();
                ContentResolver resolver = mContext.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,    //显示的名称
                        MediaStore.Video.Media.DURATION,        //总时长
                        MediaStore.Video.Media.SIZE,            //文件大小
                        MediaStore.Video.Media.DATA,            //绝对地址
                        MediaStore.Video.Media.ARTIST           //作家
                };
                Cursor cursor = resolver.query( uri,objs,null,null,null );
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString( 0 );
                        Long duration = cursor.getLong( 1 );
                        Long size = cursor.getLong( 2 );
                        String path = cursor.getString( 3 );
                        String artist = cursor.getString( 4 );
                        MediaItem item = new MediaItem( name,duration,size,path,artist );
                        mediaItemList.add( item ); }
                    }
                cursor.close();
                handler.sendEmptyMessage(1);
            }
        }.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
