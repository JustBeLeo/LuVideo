package com.android.sdk13.mobileplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.sdk13.mobileplayer.Base.BasePager;
import com.android.sdk13.mobileplayer.Pager.AudioPager;
import com.android.sdk13.mobileplayer.Pager.MySpacePager;
import com.android.sdk13.mobileplayer.Pager.NetVideo.NetVideoPager;
import com.android.sdk13.mobileplayer.Pager.Video.VideoPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.fl_main)
    FrameLayout fl;
    @BindView(R.id.rb_main_video)
    RadioButton rbVideo;
    @BindView(R.id.rb_main_audio)
    RadioButton rbAudio;
    @BindView(R.id.rb_main_netvideo)
    RadioButton rbNetvideo;
    @BindView(R.id.rb_main_netaudio)
    RadioButton rbNetaudio;
    @BindView(R.id.rg_main)
    RadioGroup rg;
    int position;
    FragmentManager fm;
    FragmentTransaction transaction;
    List<BasePager> mBasePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );
        applyPermission();
        initFragment();
        initButton();
    }

    private void applyPermission() {
        String[] PERMISSIONS_STORAGE = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        };    //请求状态码
        int REQUEST_PERMISSION_CODE = 2;
            if (ActivityCompat.checkSelfPermission( MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions( MainActivity.this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE );
            }
    }

    private BasePager getBasePager() {
        return null;
    }

    private void initButton() {
        RadioButton[] rbs = new RadioButton[4];
        rbs[0] = rbVideo;
        rbs[1] = rbAudio;
        rbs[2] = rbNetvideo;
        rbs[3] = rbNetaudio;
        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drawables = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect( 0, 0, drawables[1].getMinimumWidth() / 2, drawables[1].getMinimumHeight()/ 2 );
            //定义一个Rect边界
            drawables[1].setBounds( r );
            rb.setCompoundDrawables( null, drawables[1], null, null );
        }

        rg.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    default:
                        position = 0;
                    case R.id.rb_main_video:
                        position = 0;
                        break;
                    case R.id.rb_main_netvideo:
                        position = 1;
                        break;
                    case R.id.rb_main_audio:
                        position = 2;
                        break;
                    case R.id.rb_main_netaudio:
                        position = 3;
                        break;
                }
                switchFragment();
            }
        });
        rg.check( R.id.rb_main_video );
    }


    private void initFragment() {
        mBasePager = new ArrayList<>();
        mBasePager.add( new VideoPager( this ) );
        mBasePager.add( new NetVideoPager(this) );
        mBasePager.add(new AudioPager(this) );
        mBasePager.add(new MySpacePager(this) );

        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        for(int i=0;i<=3;i++){
            transaction.add(R.id.fl_main, mBasePager.get(i) );
            transaction.hide( mBasePager.get(i) );
        }
        transaction.show( mBasePager.get(0) );
        transaction.commit();
    }

    public void switchFragment(){
        transaction = fm.beginTransaction();
        for(int i=0;i<=3;i++)
            if(mBasePager.get(i)!=null)
                transaction.hide( mBasePager.get(i) );
        transaction.show( mBasePager.get(position) );
        transaction.commit();
    }

    public void text(View v){
        Toast.makeText( this,"点击了text" ,Toast.LENGTH_SHORT).show();
    }
}
