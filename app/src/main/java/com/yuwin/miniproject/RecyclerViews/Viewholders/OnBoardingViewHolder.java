package com.yuwin.miniproject.RecyclerViews.Viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.R;

public class OnBoardingViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTextView;

    public OnBoardingViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.onBoardImageView);
        mTextView = itemView.findViewById(R.id.onboardTextView);
    }
}
