package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class DiscoverRestaurantViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView restaurantName;
    public TextView restaurantDistance;

    public DiscoverRestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.restaurantImageView);
        restaurantName = itemView.findViewById(R.id.restaurantNameTextView);
        restaurantDistance = itemView.findViewById(R.id.restaurantDistanceTextView);
    }
}
