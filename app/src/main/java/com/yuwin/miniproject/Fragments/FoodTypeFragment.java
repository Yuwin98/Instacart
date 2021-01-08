package com.yuwin.miniproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yuwin.miniproject.RecyclerViews.Adapters.FoodTypeAdapter;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.ViewModels.FoodTypeVMFactory;
import com.yuwin.miniproject.ViewModels.FoodTypeViewModel;

public class FoodTypeFragment extends Fragment {

    private FoodTypeAdapter mFoodTypeAdapter;
    private FoodTypeViewModel mViewModel;


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mFoodTypeAdapter = new FoodTypeAdapter(getContext());
        mViewModel = new ViewModelProvider(this, new FoodTypeVMFactory(getActivity().getApplication())).get(FoodTypeViewModel.class);
        mViewModel.get_foodTypes().observe(getViewLifecycleOwner(), foodTypes -> {
            mFoodTypeAdapter.setData(foodTypes);
        });
        View view = inflater.inflate(R.layout.fragment_food_types, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.foodTypeRecyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mFoodTypeAdapter);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.bootomNavigationView);
        navigationView.setVisibility(View.VISIBLE);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);



    }


}