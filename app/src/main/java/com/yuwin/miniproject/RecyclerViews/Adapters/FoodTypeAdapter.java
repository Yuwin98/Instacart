package com.yuwin.miniproject.RecyclerViews.Adapters;


import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.yuwin.miniproject.DB.Entity.FoodTypeEntity;
import com.yuwin.miniproject.RecyclerViews.Viewholders.FoodTypeViewHolder;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeViewHolder> {

    List<FoodTypeEntity> data = new ArrayList<>();
    private Context mContext;

    public FoodTypeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public FoodTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_type_item, null);
        return new FoodTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTypeViewHolder holder, int position) {

        holder.mFoodTypeTextView.setText(data.get(position).getFoodTypeName());
        int imgID = Utility.getResourceId(mContext, data.get(position).foodTypeImageName);
        holder.mFoodTypeImageView.setImageResource(imgID);

        holder.mItemBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mFoodTypeImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.White));
                holder.mItemBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Green));
                holder.mFoodTypeTextView.setTextColor(ContextCompat.getColor(mContext, R.color.White));
                Navigation.findNavController(view)
                        .navigate(R.id.action_FoodItemFragment_to_discoverFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FoodTypeEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

}
