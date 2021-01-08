package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.yuwin.miniproject.Models.AvailableMeal;
import com.yuwin.miniproject.RecyclerViews.Adapters.AvailableMealAdapter;
import com.yuwin.miniproject.R;

import java.util.ArrayList;
import java.util.List;


public class SelectedPlaceFragment extends Fragment {

    RecyclerView mTopOffersRecyclerView;
    RecyclerView mMostPopularRecyclerView;
    AvailableMealAdapter mAvailableMealAdapter;
    ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_selected_place, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = view.findViewById(R.id.selectedPlaceProgressBar);
        mTopOffersRecyclerView = view.findViewById(R.id.topOffersRecyclerView);
        mMostPopularRecyclerView = view.findViewById(R.id.mostPopularRecyclerView);
        mMostPopularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        mTopOffersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAvailableMealAdapter = new AvailableMealAdapter();
        getData();
    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<AvailableMeal> data = new ArrayList<>();
        db.collection("foods")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            data.add(new AvailableMeal(
                                    document.getData().get("img").toString(),
                                    document.getData().get("name").toString(),
                                    document.getData().get("price").toString()
                            ));
                        }

                    } else {
                        Log.d("fb_data", "Error Getting documents", task.getException());
                    }
                    mAvailableMealAdapter.setData(data);
                    mTopOffersRecyclerView.setAdapter(mAvailableMealAdapter);
                    mMostPopularRecyclerView.setAdapter(mAvailableMealAdapter);
                    mProgressBar.setVisibility(View.GONE);
                });

    }
}