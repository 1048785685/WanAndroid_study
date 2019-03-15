package com.example.liuyang05_sx.androidstudy.ui.save;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuyang05_sx.androidstudy.R;

import com.example.liuyang05_sx.androidstudy.bean.save.ShowSaveData_;
import com.example.liuyang05_sx.androidstudy.utils.Time;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShowSaveData_> mList;
    private SaveOnRecyclerView saveOnRecyclerView;
    public SaveAdapter(Context context,List<ShowSaveData_> list){
        this.context = context;
        mList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.recycler_save_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            ((ViewHolder) viewHolder).save_item_author.setText(mList.get(i).getAuthor());
            ((ViewHolder) viewHolder).save_item_like.setImageResource(R.drawable.icon_like_selected);
            ((ViewHolder) viewHolder).save_item_tag.setText(mList.get(i).getChapterName());
            ((ViewHolder) viewHolder).save_item_time.setText(Time.getTime("yyyy-MM-dd",mList.get(i).getPublishTime()));
            ((ViewHolder) viewHolder).save_item_title.setText(mList.get(i).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.save_item_author)
        TextView save_item_author;
        @BindView(R.id.save_item_title)
        TextView save_item_title;
        @BindView(R.id.save_item_time)
        TextView save_item_time;
        @BindView(R.id.save_item_tag)
        TextView save_item_tag;
        @BindView(R.id.save_item_like)
        ImageView save_item_like;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            save_item_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveOnRecyclerView.onLikeClick(getLayoutPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveOnRecyclerView.onItemClick();
                }
            });
        }
    }
    public void dataChanged(List<ShowSaveData_> list){
        mList = list;
        notifyDataSetChanged();
    }
    interface SaveOnRecyclerView{
        void onLikeClick(int position);
        void onItemClick();
    }
    public void OnSaveRecyclerClick(SaveOnRecyclerView onRecyclerView){
        saveOnRecyclerView = onRecyclerView;
    }
}
