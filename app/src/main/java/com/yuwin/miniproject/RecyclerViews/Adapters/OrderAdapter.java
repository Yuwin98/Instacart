package com.yuwin.miniproject.RecyclerViews.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuwin.miniproject.Models.OrderModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Viewholders.OrderViewHolder;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private List<OrderModel> data;

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, null);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.orderName.setText(data.get(position).getMealName());
        holder.orderPrice.setText(data.get(position).getTotalPrice());
        holder.orderStatus.setText(data.get(position).getProgress());
        holder.orderPlacedTime.setText(data.get(position).getDateTime());
        String imgUrl = data.get(position).getImgUrl();
        Glide.with(holder.itemView).load(imgUrl).into(holder.orderMealImage);

        holder.orderLayout.setOnClickListener(v -> {
            Navigation.findNavController(holder.itemView)
            .navigate(R.id.action_orderFragment_to_orderTrackFragment);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<OrderModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
