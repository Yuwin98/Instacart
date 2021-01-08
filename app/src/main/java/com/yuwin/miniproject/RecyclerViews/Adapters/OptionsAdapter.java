package com.yuwin.miniproject.RecyclerViews.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuwin.miniproject.Models.OptionsModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Viewholders.OptionsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsViewHolder> {

    private List<OptionsModel> data = new ArrayList<>();
    private Context mContext;
    private float mealPrice;
    private TextView mealPriceTextView;
    private float optionsPrice = 0;

    public OptionsAdapter(Context context, float mealPrice, TextView mealPriceTextView) {
        mContext = context;
        this.mealPrice = mealPrice;
        this.mealPriceTextView = mealPriceTextView;
    }

    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.options_item, null);
        return new OptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
        holder.mOptionsNameTextView.setText(data.get(position).getName());
        String url = data.get(position).getImg();
        Glide.with(holder.itemView).load(url).into(holder.mOptionsImage);

        holder.mOptionsImage.setOnClickListener(view -> {

            Boolean isSelected = data.get(position).getSelected();
            if (!isSelected) {
                optionsPrice += data.get(position).getPriceAsFloat();
                holder.optionsCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.Green));
                holder.mOptionsNameTextView.setTextColor(ContextCompat.getColor(mContext, R.color.White));
            } else {
                optionsPrice -=  data.get(position).getPriceAsFloat();
                holder.optionsCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.White));
                holder.mOptionsNameTextView.setTextColor(ContextCompat.getColor(mContext, R.color.LightBlack));
            }
            mealPriceTextView.setText((mealPrice + optionsPrice) + "$");
            data.get(position).setSelected(!isSelected);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<OptionsModel> data) {
        this.data = data;
    }
}
