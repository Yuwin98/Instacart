package com.yuwin.miniproject.RecyclerViews.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuwin.miniproject.Models.FavouriteModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Viewholders.FavouriteViewHolder;

import java.util.ArrayList;
import java.util.List;


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteViewHolder> {

    List<FavouriteModel> data;
    Context mContext;

    public FavouriteAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, null);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        holder.restaurantName.setText(data.get(position).getName());
        holder.restaurantHours.setText(data.get(position).getHours());
        holder.restaurantStars.setText(data.get(position).getStars());
        String url = data.get(position).getImg();
        Glide.with(holder.itemView).load(url).into(holder.restaurantImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FavouriteModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
