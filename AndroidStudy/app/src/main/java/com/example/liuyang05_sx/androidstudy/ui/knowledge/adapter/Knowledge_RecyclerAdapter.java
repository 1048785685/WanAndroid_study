package com.example.liuyang05_sx.androidstudy.ui.knowledge.adapter;

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

public class Knowledge_RecyclerAdapter extends RecyclerView.Adapter<Knowledge_RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private OnRecyclerListener monRecyclerListener;
    public Knowledge_RecyclerAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_knowledge,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mKnowledge_content.setText("Android Studio相关   gradle  官方发布");
        viewHolder.mKnowledge_image.setImageResource(R.drawable.ic_toright);
        viewHolder.mKnowledge_title.setText("开发环境");
        if (monRecyclerListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monRecyclerListener.onItemListener(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 13;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.knowledge_title)
        TextView mKnowledge_title;
        @BindView(R.id.knowledge_content)
        TextView mKnowledge_content;
        @BindView(R.id.knowledge_image)
        ImageView mKnowledge_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    public interface OnRecyclerListener{
        void onItemListener(int position);
    }

    public void setOnRecyclerListener(OnRecyclerListener onRecyclerListener){
        monRecyclerListener = onRecyclerListener;
    }
}
