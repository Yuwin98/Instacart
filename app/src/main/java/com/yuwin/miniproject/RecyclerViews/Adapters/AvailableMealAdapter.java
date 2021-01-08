package com.yuwin.miniproject.RecyclerViews.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuwin.miniproject.Fragments.SelectedPlaceFragmentDirections;
import com.yuwin.miniproject.Models.AvailableMeal;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Viewholders.AvailableMealViewHolder;

import java.util.List;

public class AvailableMealAdapter extends RecyclerView.Adapter<AvailableMealViewHolder> {

    private List<AvailableMeal> topOffers;

    @NonNull
    @Override
    public AvailableMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_meal_item, null);
        return new AvailableMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableMealViewHolder holder, int position) {

        holder.mealName.setText(topOffers.get(position).getMealName());
        holder.mealPrice.setText(topOffers.get(position).getMealPrice());
        String url = topOffers.get(position).getImageID();
        Glide.with(holder.itemView).load(url).into(holder.ImageID);

        holder.ImageID.setOnClickListener(view -> {
            SelectedPlaceFragmentDirections.PlaceToMeal action = SelectedPlaceFragmentDirections
                    .PlaceToMeal(topOffers.get(position));
            Navigation.findNavController(view)
                    .navigate(action);
        });

    }

    @Override
    public int getItemCount() {
        return topOffers.size();
    }

    public void setData(List<AvailableMeal> topOffers) {
        this.topOffers = topOffers;
    }


}
