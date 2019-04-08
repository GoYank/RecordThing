package com.example.record.recordthing.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.record.recordthing.R;
import com.example.record.recordthing.bean.QuestionBank;

import java.util.List;

/**
 * Author : Gyk
 * CreateBy : 2019/03/06/9:28
 * PackageName : com.example.gyk.chatim.adapter
 * Describe : TODO
 **/
public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.ViewHolder> {
    List<QuestionBank> data;

    public MyRvAdapter(List<QuestionBank> mData) {
        this.data = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_questionbank,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("gyk","topic="+data.get(position).getTopic());
        holder.tv_title.setText(data.get(position).getTopic());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
