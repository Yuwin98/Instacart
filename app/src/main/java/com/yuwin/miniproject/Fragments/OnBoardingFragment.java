package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yuwin.miniproject.Models.OnBoardingItem;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Adapters.OnBoardingAdapter;

import java.util.ArrayList;
import java.util.List;


public class OnBoardingFragment extends Fragment {

    ViewPager2 mViewPager2;
    List<OnBoardingItem> data = new ArrayList<>();

    CardView onBoardNextButton;
    TextView onBoardSkipTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data.add(new OnBoardingItem(R.drawable.ic_onboard_1, "Food You Love"));
        data.add(new OnBoardingItem(R.drawable.ic_onboard_2, "Delivered To You"));
        data.add(new OnBoardingItem(R.drawable.ic_onboard_3, "So You Can Enjoy"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.bootomNavigationView);
        navigationView.setVisibility(View.GONE);

        onBoardNextButton = view.findViewById(R.id.onBoardNextButton);
        onBoardSkipTextView = view.findViewById(R.id.onBoardSkipText);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        OnBoardingAdapter adapter = new OnBoardingAdapter();
        adapter.setData(data);
        mViewPager2 = view.findViewById(R.id.viewPager);
        mViewPager2.setAdapter(adapter);

        onBoardNextButton.setOnClickListener(v -> {
            if(mViewPager2.getCurrentItem() == 2){
                Navigation.findNavController(v).navigate(R.id.action_onBoardingFragment_to_loginFragment);
            }
            mViewPager2.setCurrentItem(mViewPager2.getCurrentItem() + 1);

        });

        onBoardSkipTextView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_onBoardingFragment_to_loginFragment));
    }


}