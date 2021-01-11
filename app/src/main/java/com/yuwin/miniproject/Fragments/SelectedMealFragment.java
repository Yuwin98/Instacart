package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.todkars.shimmer.ShimmerRecyclerView;
import com.yuwin.miniproject.Models.AvailableMeal;
import com.yuwin.miniproject.Models.OptionsModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Adapters.OptionsAdapter;

import java.util.ArrayList;
import java.util.List;


public class SelectedMealFragment extends Fragment {

    ImageView mMealImage;
    TextView mMealName;
    TextView mMealPrice;
    TextView mTotalOrdersText;
    Button mPlaceOrderButton;

    ShimmerRecyclerView mOptionsRecyclerView;
    OptionsAdapter mOptionsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AvailableMeal meal = SelectedMealFragmentArgs.fromBundle(getArguments()).getMeal();

        mOptionsRecyclerView = view.findViewById(R.id.optionsRecyclerView);
        mOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        showShimmer();
        mMealImage = view.findViewById(R.id.mealImage);
        mMealName = view.findViewById(R.id.mealName);
        mMealPrice = view.findViewById(R.id.mealPrice);
        mTotalOrdersText = view.findViewById(R.id.mealOrderNumber);
        mPlaceOrderButton = view.findViewById(R.id.placeOrderButton);

        Glide.with(view).load(meal.getImageID()).into(mMealImage);
        mMealName.setText(meal.getMealName());
        mMealPrice.setText(meal.getMealPrice());
        mTotalOrdersText.setText(meal.getMealOrders() + "K Orders");

        mPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mealPrice = mMealPrice.getText().toString();
                SelectedMealFragmentDirections.MealToUpsell action = SelectedMealFragmentDirections.MealToUpsell(mealPrice);
                Navigation.findNavController(view).navigate(action);
            }
        });

        mOptionsAdapter = new OptionsAdapter(getActivity().getApplicationContext(), meal.getMealPriceAsNumber(), mMealPrice);
        getData();
    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<OptionsModel> data = new ArrayList<>();
        db.collection("options")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            data.add(new OptionsModel(
                                    document.getData().get("img").toString(),
                                    document.getData().get("name").toString(),
                                    document.getData().get("price").toString()
                            ));
                        }

                    } else {
                        Log.d("options", "Error Getting documents", task.getException());
                    }
                    mOptionsAdapter.setData(data);
                    mOptionsRecyclerView.setAdapter(mOptionsAdapter);
                    hideShimmer();
                });
    }

    private void showShimmer() {
        mOptionsRecyclerView.showShimmer();
    }

    private void hideShimmer() {
        mOptionsRecyclerView.hideShimmer();
    }
}