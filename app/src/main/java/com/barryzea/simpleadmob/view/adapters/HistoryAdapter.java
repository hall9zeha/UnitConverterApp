package com.barryzea.simpleadmob.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.OnClickInterface;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.databinding.HistoryItemBinding;


import java.util.ArrayList;
import java.util.List;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<HistoryEntity> historyList = new ArrayList<>();
    private OnClickInterface listener;
    public HistoryAdapter(OnClickInterface listener){
        this.listener= listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.onBind(historyList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return historyList.isEmpty()? 0 : historyList.size();
    }
    public void addAll(List<HistoryEntity> list){
        for(HistoryEntity h : list){
            if(!historyList.contains(h)){
                historyList.add(h);
                notifyItemInserted(historyList.size()-1);
            }
        }
    }
    public void clearAll(){
        int size= historyList.size();
        historyList.clear();
        notifyItemRangeRemoved(0,size);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public HistoryItemBinding bind;
        public ViewHolder(View itemView){
            super(itemView);
            bind=HistoryItemBinding.bind(itemView);
        }
        public void onBind(HistoryEntity history, OnClickInterface listener){
            bind.tvUnitValueFrom.setText(bind.getRoot().getContext().getString(R.string.convertFrom,String.valueOf(history.getFromUnitValue()),history.getFromUnitName()));
            bind.tvUnitValueTo.setText(bind.getRoot().getContext().getString(R.string.convertTo,
                    String.valueOf(history.getToUnitValue()),history.getToUnitName(),
                    String.valueOf(history.getScientificNotation())));
            bind.getRoot().setOnClickListener(v->listener.onClick(bind.getRoot(),history));
        }
    }
}
