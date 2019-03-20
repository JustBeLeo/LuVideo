package com.android.sdk13.mobileplayer.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.sdk13.mobileplayer.Domain.MediaItem;
import com.android.sdk13.mobileplayer.R;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoPlayer extends AppCompatActivity {
    boolean firstFlag = true;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.tv_video_player)
    TextView tv_video_player;
    JCVideoPlayerStandard myJzvdStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_video_player );
        ButterKnife.bind( this );
        myJzvdStd = findViewById( R.id.videoplayer );
        EventBus.getDefault().register( this );
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void recieve(MediaItem item) {
        // Toast.makeText( VideoPlayer.this,item.getPath(),Toast.LENGTH_SHORT ).show();
        tv_video_player.setText(item.getPath() );
        myJzvdStd.setUp( item.getPath(), item.getName() );
        Glide.with( VideoPlayer.this ).load( item.getPath() ).into( myJzvdStd.thumbImageView );
        myJzvdStd.startButton.performClick();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myJzvdStd.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister( this );
    }
}
