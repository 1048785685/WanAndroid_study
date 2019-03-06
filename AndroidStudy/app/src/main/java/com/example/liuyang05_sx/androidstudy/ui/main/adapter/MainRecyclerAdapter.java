package com.example.liuyang05_sx.androidstudy.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.bean.main.Data;
import com.example.liuyang05_sx.androidstudy.bean.main.Data_;
import com.example.liuyang05_sx.androidstudy.bean.main.Main_Banner;
import com.example.liuyang05_sx.androidstudy.utils.GlideImageLoader;
import com.example.liuyang05_sx.androidstudy.utils.Time;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnRecyclerViewListener onRecyclerViewListener;
    private final int banner_View = 0;
    private final int other_Item = 1;
    private List<String> title = new ArrayList<>();
    private List<String> image = new ArrayList<>();
    private List<String> url = new ArrayList<>();
    private List<Main_Banner> mData;
    private List<Data_>  mMainData;
    private Banner mBanner;
    public MainRecyclerAdapter(Context context,List<Main_Banner> lists,List<Data_> mMainData){
        mContext = context;
        mData = lists;
        this.mMainData = mMainData;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == banner_View){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_main_first,viewGroup,false);
            return new BannerViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_main_item,viewGroup,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof BannerViewHolder){
            image.clear();
            title.clear();
            url.clear();
            for (Main_Banner banner_data : mData){
                image.add(banner_data.getImagePath());
                title.add(banner_data.getTitle());
                url.add(banner_data.getUrl());
            }
            ((BannerViewHolder) viewHolder).banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImageLoader(new GlideImageLoader()).setImages(image)
                    .setBannerAnimation(Transformer.DepthPage)
                    .setBannerTitles(title)
                    .isAutoPlay(true)
                    .setDelayTime(2000)
                    .start();
        }else if (viewHolder instanceof ViewHolder){
            ((ViewHolder)viewHolder).main_item_author.setText(mMainData.get(i).getAuthor());
            ((ViewHolder)viewHolder).main_item_tag.setText(mMainData.get(i).getSuperChapterName()+"/"+mMainData.get(i).getChapterName());
            ((ViewHolder)viewHolder).main_item_time.setText(Time.getTime("yyyy-MM-dd",mMainData.get(i).getPublishTime()));
            ((ViewHolder)viewHolder).main_item_title.setText(Html.fromHtml(mMainData.get(i).getTitle()));
        }
    }



    @Override
    public int getItemCount() {
        return mMainData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0){
            return banner_View;
        }else if (position>0){
            return other_Item;
        }else{
        return super.getItemViewType(position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.main_item_author)
        TextView main_item_author;
        @BindView(R.id.main_item_title)
        TextView main_item_title;
        @BindView(R.id.main_item_time)
        TextView main_item_time;
        @BindView(R.id.main_item_tag)
        TextView main_item_tag;
        @BindView(R.id.main_item_like)
        ImageView main_item_like;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            if (onRecyclerViewListener!=null){
                main_item_like.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        onRecyclerViewListener.onLikeClick(v);
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewListener.onItemClick(mMainData.get(getLayoutPosition()).getLink(),
                                String.valueOf(Html.fromHtml(mMainData.get(getLayoutPosition()).getTitle())));

                    }
                });
            }
        }
    }
    public class BannerViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner banner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            if (onRecyclerViewListener!=null){
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        onRecyclerViewListener.onBannerClick(mData.get(position).getUrl(),mData.get(position).getTitle());
                    }
                });
            }
        }
    }

    public void replaceManiData(List<Data_> list){
        mMainData = list;
        notifyDataSetChanged();
    }

    public void addData(List<Data_> list){
        mMainData.addAll(list);
        notifyDataSetChanged();
    }
    public interface OnRecyclerViewListener{
        void onItemClick(String url,String title);
        void onLikeClick(View view);
        void onBannerClick(String url,String title);
    }
    public void setOnRecycleViewListener(OnRecyclerViewListener itemClickListener){
        this.onRecyclerViewListener = itemClickListener;
    }

}
