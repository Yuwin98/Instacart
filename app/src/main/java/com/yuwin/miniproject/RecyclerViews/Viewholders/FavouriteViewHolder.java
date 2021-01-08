package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class FavouriteViewHolder extends RecyclerView.ViewHolder {

    public ImageView restaurantImage;
    public TextView restaurantName;
    public TextView restaurantHours;
    public TextView restaurantStars;

    public FavouriteViewHolder(@NonNull View itemView) {
        super(itemView);
        restaurantImage = itemView.findViewById(R.id.fvImageView);
        restaurantName = itemView.findViewById(R.id.fvNameTextView);
        restaurantHours = itemView.findViewById(R.id.fvHoursTextView);
        restaurantStars = itemView.findViewById(R.id.fvStarsTextView);
    }
}
