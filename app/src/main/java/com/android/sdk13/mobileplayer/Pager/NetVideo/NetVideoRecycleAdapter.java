package com.android.sdk13.mobileplayer.Pager.NetVideo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.sdk13.mobileplayer.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class NetVideoRecycleAdapter extends RecyclerView.Adapter<NetVideoRecycleAdapter.NetVideoViewHolder> {


    private Context mContext;
    private ArrayList<MovieInfo> data;
    public Unbinder unbinder;

    public NetVideoRecycleAdapter(Context mContext, ArrayList<MovieInfo> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public NetVideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate( mContext, R.layout.item_netvideo, null );
        return new NetVideoViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull NetVideoViewHolder vh, int i) {
        vh.tv.setText( data.get( i).getSummary() );
        Glide.with( mContext )
                .load( data.get( i).getCoverImg() )
        .into( vh.jc.thumbImageView );
        vh.jc.setUp( data.get( i ).getUrl(),data.get(i).getMovieName() );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NetVideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jc_netvideo)
        JCVideoPlayerStandard jc;
        @BindView(R.id.tv_item_netvideo_describe)
        TextView tv;

        public NetVideoViewHolder(@NonNull View itemView) {
            super( itemView );
            unbinder = ButterKnife.bind( this, itemView );
        }
    }

}
