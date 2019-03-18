package com.example.liuyang05_sx.androidstudy.ui.knowledge.adapter;

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

public class Knowledge_Detail_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnRecyclerViewListener onRecyclerViewListener;
    private List<Main_Banner> mData;
    private List<Data_>  mMainData;
    public Knowledge_Detail_Adapter(Context context,List<Data_> mMainData){
        mContext = context;
        this.mMainData = mMainData;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_main_item,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            ((ViewHolder)viewHolder).main_item_author.setText(mMainData.get(i).getAuthor());
            ((ViewHolder)viewHolder).main_item_tag.setText(mMainData.get(i).getSuperChapterName()+"/"+mMainData.get(i).getChapterName());
            ((ViewHolder)viewHolder).main_item_time.setText(Time.getTime("yyyy-MM-dd",mMainData.get(i).getPublishTime()));
            ((ViewHolder)viewHolder).main_item_title.setText(Html.fromHtml(mMainData.get(i).getTitle()));
            if (mMainData.get(i).getCollect()){
                Log.d("xxx",mMainData.get(i).getTitle()+"            ");
                ((ViewHolder) viewHolder).main_item_like.setImageResource(R.drawable.icon_like_selected);
            }else {
                ((ViewHolder) viewHolder).main_item_like.setImageResource(R.drawable.icon_like_article_not_selected);
            }

        }
    }



    @Override
    public int getItemCount() {
        return mMainData.size();
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
                        onRecyclerViewListener.onLikeClick(main_item_like,mMainData.get(getLayoutPosition()).getId());
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
    public void replaceMainData(List<Data_> list){
        mMainData = list;
        notifyDataSetChanged();
    }

    public void addData(List<Data_> list){
        mMainData.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnRecyclerViewListener{
        void onItemClick(String url,String title);
        void onLikeClick(ImageView imageView,int id);
    }
    public void setOnRecycleViewListener(OnRecyclerViewListener itemClickListener){
        this.onRecyclerViewListener = itemClickListener;
    }

}

