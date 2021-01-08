package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class OptionsViewHolder extends RecyclerView.ViewHolder {

    public ImageView mOptionsImage;
    public TextView mOptionsNameTextView;
    public CardView optionsCard;


    public OptionsViewHolder(@NonNull View itemView) {
        super(itemView);
        mOptionsImage = itemView.findViewById(R.id.optionImage);
        mOptionsNameTextView = itemView.findViewById(R.id.optionName);
        optionsCard = itemView.findViewById(R.id.optionCard);

    }
}
