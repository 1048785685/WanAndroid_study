package com.example.liuyang05_sx.androidstudy.ui.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.bean.project.pro_Data_;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Project_Detail_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<pro_Data_> data;
    private Context context;
    private ProjectClickListener clicklistener;
    public Project_Detail_Adapter(Context context,List<pro_Data_> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_pro_detail,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            ((ViewHolder) viewHolder).item_project_detail_author_tv.setText(data.get(i).getAuthor());
            ((ViewHolder) viewHolder).item_project_detail_content_tv.setText(data.get(i).getDesc());
            ((ViewHolder) viewHolder).item_project_detail_time_tv.setText(data.get(i).getNiceDate());
            ((ViewHolder) viewHolder).item_project_detail_title_tv.setText(data.get(i).getTitle());
            Glide.with(context).load(data.get(i).getEnvelopePic()).into(((ViewHolder) viewHolder).item_project_detail_iv);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_project_detail_iv)
        ImageView item_project_detail_iv;
        @BindView(R.id.item_project_detail_title_tv)
        TextView item_project_detail_title_tv;
        @BindView(R.id.item_project_detail_content_tv)
        TextView item_project_detail_content_tv;
        @BindView(R.id.item_project_detail_author_tv)
        TextView item_project_detail_author_tv;
        @BindView(R.id.item_project_detail_time_tv)
        TextView item_project_detail_time_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicklistener.proItemLisenter(data.get(getLayoutPosition()).getTitle(),
                            data.get(getLayoutPosition()).getLink());
                }
            });
        }
    }
    public void replaceManiData(List<pro_Data_> list){
        data = list;
        notifyDataSetChanged();
    }

    public void addData(List<pro_Data_> list){
        data.addAll(list);
        notifyDataSetChanged();
    }
    interface ProjectClickListener{
        void proItemLisenter(String title,String url);
    }
   public void setProClick(ProjectClickListener listener){
        this.clicklistener = listener;
   }
}
