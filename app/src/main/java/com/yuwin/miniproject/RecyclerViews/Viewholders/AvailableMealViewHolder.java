package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class AvailableMealViewHolder extends RecyclerView.ViewHolder {

    public ImageView ImageID;
    public TextView mealName;
    public TextView mealPrice;

    public AvailableMealViewHolder(@NonNull View itemView) {
        super(itemView);
        ImageID = itemView.findViewById(R.id.availableMealImageView);
        mealName = itemView.findViewById(R.id.availableMealName);
        mealPrice = itemView.findViewById(R.id.availableMealPrice);
    }
}
