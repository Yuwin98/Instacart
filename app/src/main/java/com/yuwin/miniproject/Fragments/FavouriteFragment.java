package com.yuwin.miniproject.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.yuwin.miniproject.Models.FavouriteModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Adapters.FavouriteAdapter;


import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {


    RecyclerView favouriteRecyclerView;
    FavouriteAdapter mFavouriteAdapter;
    ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = view.findViewById(R.id.favProgressBar);
        favouriteRecyclerView = view.findViewById(R.id.favouriteRecyclerView);
        favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFavouriteAdapter = new FavouriteAdapter(getContext());
        getData();

    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<FavouriteModel> data = new ArrayList<>();
        db.collection("favourites")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("fb_data", String.valueOf(document.getData()));
                            data.add(new FavouriteModel(
                                    document.getData().get("name").toString(),
                                    document.getData().get("img").toString(),
                                    document.getData().get("stars").toString(),
                                    document.getData().get("hours").toString()
                            ));
                        }

                    } else {
                        Log.d("fb_data", "Error Getting documents", task.getException());
                    }
                    mFavouriteAdapter.setData(data);
                    favouriteRecyclerView.setAdapter(mFavouriteAdapter);
                    mProgressBar.setVisibility(View.GONE);
                });

    }


}