package com.example.liuyang05_sx.androidstudy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

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
    public MainRecyclerAdapter(Context context){
        mContext = context;
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
        image.add("http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png");
        image.add("http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png");
        image.add("http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");
        title.add("看看别人的面经，搞定面试~");
        title.add("兄弟，要不要挑个项目学习下?");
        title.add("我们新增了一个常用导航Tab");
        if (viewHolder instanceof BannerViewHolder){
            ((BannerViewHolder) viewHolder).banner.setImageLoader(new GlideImageLoader());
            ((BannerViewHolder) viewHolder).banner.setImages(image)
            .setBannerAnimation(Transformer.DepthPage)
            .setBannerTitles(title)
            .isAutoPlay(true)
            .setDelayTime(1000)
            .start();
        }else if (viewHolder instanceof ViewHolder){
            ((ViewHolder)viewHolder).main_item_author.setText("作者");
            ((ViewHolder)viewHolder).main_item_tag.setText("公众号、任玉刚");
            ((ViewHolder)viewHolder).main_item_time.setText("一天之前");
            ((ViewHolder)viewHolder).main_item_title.setText("Android学习指南");
        }
    }



    @Override
    public int getItemCount() {
        return 5;
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
                main_item_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewListener.onLikeClick(v);
//                        Toast.makeText(mContext,"点击收藏按钮",Toast.LENGTH_SHORT);
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRecyclerViewListener.onItemClick(v);
//                        Toast.makeText(mContext,"点击item",Toast.LENGTH_SHORT);
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

            }
        }
    }
    public interface OnRecyclerViewListener{
        void onItemClick(View view);
        void onLikeClick(View view);
    }
    public void setOnRecycleViewListener(OnRecyclerViewListener itemClickListener){
        this.onRecyclerViewListener = itemClickListener;
    }
}
