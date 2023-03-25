package com.barryzea.simpleadmob.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.OnClickInterface;
import com.barryzea.simpleadmob.common.entities.MeasureCardEntity;
import com.barryzea.simpleadmob.databinding.CardMeasureItemBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class MeasureAdapter extends RecyclerView.Adapter<MeasureAdapter.ViewHolder> {

    private ArrayList<MeasureCardEntity> cardList=new ArrayList<>();
    private OnClickInterface listener;
    public MeasureAdapter(OnClickInterface listener){
        this.listener= listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_measure_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding(cardList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
    public void addAll(List<MeasureCardEntity> cards){
        for(MeasureCardEntity mCard: cards){
            cardList.add(mCard);
            notifyItemInserted(cardList.size()-1);
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardMeasureItemBinding bind;
        public ViewHolder(View itemView){
            super(itemView);
            bind= CardMeasureItemBinding.bind(itemView);
        }
        public void binding(MeasureCardEntity card, OnClickInterface listener){
            bind.tvMeasureName.setText(card.getNameCard());
            Glide.with(bind.getRoot().getContext())
                    .load(card.getImageRes())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(bind.ivMeasure);
            bind.getRoot().setOnClickListener(v->{
                listener.onCardClick(card);
            });
        }
    }
}
