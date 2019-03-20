package com.android.sdk13.mobileplayer.Pager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sdk13.mobileplayer.Domain.MediaItem;
import com.android.sdk13.mobileplayer.MainActivity;
import com.android.sdk13.mobileplayer.Pager.VideoPager;
import com.android.sdk13.mobileplayer.R;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPagerAdapter extends RecyclerView.Adapter<VideoPagerAdapter.MyViewHolder> {

    private Context mContext;
    private List<MediaItem> mediaItems;
    private int postion;

    public VideoPagerAdapter(Context mContext, List<MediaItem> mediaItems) {
        this.mContext = mContext;
        this.mediaItems = mediaItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup 0viewGroup, int i) {
        postion = i;
        View itemView = View.inflate( mContext, R.layout.item_video, null );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with( mContext )
                .load( mediaItems.get( i ).getPath() )
                .into( myViewHolder.cover );
        myViewHolder.title.setText( mediaItems.get( i ).getName() );
        myViewHolder.duration.setText( generateTime(mediaItems.get( i ).getDuration()) );
        myViewHolder.introduce.setText( mediaItems.get( i ).getName() + "的介绍" );
        if (mOnItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(i);
                }
            }); }
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_video_cover)
        public ImageView cover;
        @BindView(R.id.tv_item_video_title)
        public TextView title;
        @BindView(R.id.tv_item_video_introduce)
        public  TextView introduce;
        @BindView(R.id.tv_item_video_duration)
        public TextView duration;

        public MyViewHolder(View itemView) {
            super( itemView );
            ButterKnife.bind( this, itemView );

        }
    }


    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

}
