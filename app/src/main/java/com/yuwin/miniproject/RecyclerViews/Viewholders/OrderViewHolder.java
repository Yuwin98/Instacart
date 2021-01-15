package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView orderName;
    public TextView orderPrice;
    public TextView orderStatus;
    public TextView orderPlacedTime;
    public ImageView orderMealImage;
    public ConstraintLayout orderLayout;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        orderName = itemView.findViewById(R.id.mealNameTextView);
        orderPrice = itemView.findViewById(R.id.mealPriceTextView);
        orderStatus = itemView.findViewById(R.id.orderStatusTextView);
        orderPlacedTime = itemView.findViewById(R.id.orderPlacedTimeTextView);
        orderMealImage = itemView.findViewById(R.id.mealImageView);
        orderLayout = itemView.findViewById(R.id.orderLayout);
    }
}
