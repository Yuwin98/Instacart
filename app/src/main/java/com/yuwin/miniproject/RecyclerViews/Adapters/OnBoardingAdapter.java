package com.yuwin.miniproject.RecyclerViews.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.yuwin.miniproject.Models.OnBoardingItem;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Viewholders.OnBoardingViewHolder;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingViewHolder> {


    List<OnBoardingItem> data;


    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_layout, parent, false);
        return new OnBoardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.mImageView.setImageResource(data.get(position).getOnBoardingImage());
        holder.mTextView.setText(data.get(position).getOnBoardingText());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<OnBoardingItem> data) {
        this.data = data;
    }
}
