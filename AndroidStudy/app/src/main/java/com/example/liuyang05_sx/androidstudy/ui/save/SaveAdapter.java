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

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public SaveAdapter(){

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.recycler_save_item,viewGroup);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        return 0;
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
        }
    }
}
