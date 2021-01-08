package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class FoodTypeViewHolder extends RecyclerView.ViewHolder {

    public   ImageView mFoodTypeImageView;
    public TextView mFoodTypeTextView;
    public ConstraintLayout mItemBackground;

    public FoodTypeViewHolder(@NonNull View itemView) {
        super(itemView);
        mFoodTypeImageView = itemView.findViewById(R.id.foodTypeImageView);
        mFoodTypeTextView  = itemView.findViewById(R.id.foodTypeText);
        mItemBackground = itemView.findViewById(R.id.itemBackground);
    }
}
