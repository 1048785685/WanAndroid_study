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
    public boolean isMain;
    private List<String> title = new ArrayList<>();
    private List<String> image = new ArrayList<>();
    private List<String> url = new ArrayList<>();
    private List<Main_Banner> mData;
    private List<Data_>  mMainData;
    public MainRecyclerAdapter(Context context,List<Main_Banner> lists,List<Data_> mMainData,boolean flag){
        mContext = context;
        mData = lists;
        this.mMainData = mMainData;
        isMain = flag;
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
            ((ViewHolder)viewHolder).main_item_author.setText(mMainData.get(i-1).getAuthor());
            ((ViewHolder)viewHolder).main_item_tag.setText(mMainData.get(i-1).getSuperChapterName()+"/"+mMainData.get(i-1).getChapterName());
            ((ViewHolder)viewHolder).main_item_time.setText(Time.getTime("yyyy-MM-dd",mMainData.get(i-1).getPublishTime()));
            ((ViewHolder)viewHolder).main_item_title.setText(Html.fromHtml(mMainData.get(i-1).getTitle()));
            if (mMainData.get(i-1).getCollect()){
                ((ViewHolder) viewHolder).main_item_like.setImageResource(R.drawable.icon_like_selected);
            }else {
                ((ViewHolder) viewHolder).main_item_like.setImageResource(R.drawable.icon_like_article_not_selected);
            }

        }
    }



    @Override
    public int getItemCount() {
        return mMainData.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (!isMain){
            return other_Item;
        }
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
                        onRecyclerViewListener.onLikeClick(mMainData.get(getLayoutPosition()-1).getId()
                                ,mMainData.get(getLayoutPosition()-1).getCollect());
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewListener.onItemClick(getLayoutPosition()-1);

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
                        onRecyclerViewListener.onBannerClick(position);
                    }
                });
            }
        }
    }

    public void replaceMainData(List<Data_> list){
        mMainData = list;
        notifyDataSetChanged();
    }

    public void addData(List<Data_> list){
        mMainData.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnRecyclerViewListener{
        void onItemClick(int position);
        void onLikeClick(int id,boolean isLike);
        void onBannerClick(int position);
    }
    public void setOnRecycleViewListener(OnRecyclerViewListener itemClickListener){
        this.onRecyclerViewListener = itemClickListener;
    }

}
