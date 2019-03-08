package com.example.liuyang05_sx.androidstudy.ui.navigation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuyang05_sx.androidstudy.R;
import com.example.liuyang05_sx.androidstudy.bean.navigation.Articles;
import com.example.liuyang05_sx.androidstudy.bean.navigation.NavData;
import com.example.liuyang05_sx.androidstudy.utils.CommonUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Nav_Recycler_Adapter extends RecyclerView.Adapter<Nav_Recycler_Adapter.ViewHolder>{
    private List<NavData> list;
    private Context context;
    private OnTagCListener onTagListener;
    public Nav_Recycler_Adapter(Context context,List<NavData> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_nav_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        List<Articles> mArticles = list.get(i).getList();
        if (viewHolder instanceof ViewHolder){
            viewHolder.item_navigation_text.setText(list.get(i).getName());
            viewHolder.item_navigation_tagflow.setAdapter(new TagAdapter<Articles>(mArticles) {
                @Override
                public View getView(FlowLayout parent, int position, Articles articles) {
                    TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_nav_textview,
                            viewHolder.item_navigation_tagflow, false);
                    textView.setText(articles.getTitle());
                    textView.setTextColor(CommonUtil.randomColor());
                    return textView;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void changedData(List<NavData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_navigation_text)
        TextView item_navigation_text;
        @BindView(R.id.item_navigation_tagflow)
        TagFlowLayout item_navigation_tagflow;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            item_navigation_tagflow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    onTagListener.onTagClickEvent(getLayoutPosition(),position);
                    return true;
                }
            });
        }

    }
    public interface OnTagCListener{
        void onTagClickEvent(int position,int i);
    }
    public void setOnTagClick(OnTagCListener onTagClick){
        onTagListener = onTagClick;
    }
}
