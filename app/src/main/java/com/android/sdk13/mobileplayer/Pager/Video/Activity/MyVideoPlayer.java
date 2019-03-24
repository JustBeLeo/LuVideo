package com.android.sdk13.mobileplayer.Pager.Video.Activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.sdk13.mobileplayer.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyVideoPlayer extends AppCompatActivity {

    @BindView(R.id.vv_my_video_player)
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_video_player );
        ButterKnife.bind( this );

        //设置准备完毕的监听
        video.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                video.start();
            }
        } );

        //播放错误的监听
        video.setOnErrorListener( new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(MyVideoPlayer.this,"出现错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        } );

        //播放完毕的监听
        video.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MyVideoPlayer.this,"播放完了",Toast.LENGTH_SHORT).show();
            }
        } );

        //设置控制栏
        video.setMediaController( new MediaController( this ) );

        //注册EventBus(传入Uri)
        EventBus.getDefault().register( this );
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    public void recieve(String path){
        video.setVideoPath( path );
    }
}
