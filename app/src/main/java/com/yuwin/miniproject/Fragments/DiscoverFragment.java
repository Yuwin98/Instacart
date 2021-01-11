package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.todkars.shimmer.ShimmerRecyclerView;
import com.yuwin.miniproject.Models.AvailableMeal;
import com.yuwin.miniproject.Models.DiscoverRestaurant;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Adapters.DiscoverRestaurantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DiscoverFragment extends Fragment {

    ImageView featuredImageView;
    Random mRandom = new Random();

    ShimmerRecyclerView mostPopularRecyclerView;
    ShimmerRecyclerView bestDealsRecyclerView;
    ShimmerRecyclerView highestRatedRecyclerView;



    DiscoverRestaurantAdapter mpAdapter;
    DiscoverRestaurantAdapter bdAdapter;
    DiscoverRestaurantAdapter hrAdapter;

    List<DiscoverRestaurant> mpData = new ArrayList<>();
    List<DiscoverRestaurant> bdData = new ArrayList<>();
    List<DiscoverRestaurant> hrData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mpAdapter = new DiscoverRestaurantAdapter();
        bdAdapter = new DiscoverRestaurantAdapter();
        hrAdapter = new DiscoverRestaurantAdapter();

        mostPopularRecyclerView = view.findViewById(R.id.mostPopularRecyclerView);
        bestDealsRecyclerView = view.findViewById(R.id.bestDealsRecyclerView);
        highestRatedRecyclerView = view.findViewById(R.id.highestRatedRecyclerView);
        mostPopularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bestDealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        highestRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        showShimmer();
        getData("mostpopular", mpData);
        getData("highestrated", hrData);
        getData("bestdeals", bdData);

        featuredImageView = view.findViewById(R.id.featuredImageView);
        featuredImageView.setOnClickListener(view1 -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_discoverFragment_to_selectedPlaceFragment);
        });

    }

    private void setData(String collection, List<DiscoverRestaurant> data) {
        Log.d("data", "Data is being set");
        switch (collection) {
            case "mostpopular": {
                mpAdapter.setData(data);
                mostPopularRecyclerView.setAdapter(mpAdapter);
            }break;
            case "highestrated": {
                hrAdapter.setData(data);
                highestRatedRecyclerView.setAdapter(hrAdapter);
            }break;
            case "bestdeals": {
                bdAdapter.setData(data);
                bestDealsRecyclerView.setAdapter(bdAdapter);
            }break;
        }
        hideShimmer(collection);
    }

    private void getData(String collection, List<DiscoverRestaurant> data) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(collection)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            int num = mRandom.nextInt(15) + 1;
                            String numText = num + "Km";
                            data.add(new DiscoverRestaurant(
                                    document.getData().get("img").toString(),
                                    document.getData().get("name").toString(),
                                    numText
                            ));
                        }
                    } else {
                        Log.d("fb_data", "Error Getting documents", task.getException());
                    }
                    Log.d("data", "Set data being called");
                    setData(collection, data);

                });

    }

    private void showShimmer() {
        mostPopularRecyclerView.showShimmer();
        highestRatedRecyclerView.showShimmer();
        bestDealsRecyclerView.showShimmer();
    }

    private void hideShimmer(String collection) {
        switch (collection) {
            case "mostpopular": {
                mostPopularRecyclerView.hideShimmer();
            }break;
            case "highestrated": {
                highestRatedRecyclerView.hideShimmer();
            }break;
            case "bestdeals": {
                bestDealsRecyclerView.hideShimmer();
            }break;
        }
    }
}