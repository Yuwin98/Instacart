package com.yuwin.miniproject.RecyclerViews.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuwin.miniproject.Models.DiscoverRestaurant;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Viewholders.DiscoverRestaurantViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DiscoverRestaurantAdapter extends RecyclerView.Adapter<DiscoverRestaurantViewHolder> {

    private List<DiscoverRestaurant> data;

    @NonNull
    @Override
    public DiscoverRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_item, parent, false);
        return new DiscoverRestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverRestaurantViewHolder holder, int position) {
        holder.restaurantName.setText(data.get(position).getRestaurantName());
        holder.restaurantDistance.setText(data.get(position).getRestaurantDistance());
        String url = data.get(position).getImageID();
        Glide.with(holder.itemView).load(url).into(holder.mImageView);
        holder.restaurantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.action_discoverFragment_to_selectedPlaceFragment);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return 0;
    }

    public void setData(List<DiscoverRestaurant> newData) {
        data = newData;
        notifyDataSetChanged();
    }
}
