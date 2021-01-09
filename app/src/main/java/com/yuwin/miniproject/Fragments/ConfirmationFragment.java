package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yuwin.miniproject.R;


public class ConfirmationFragment extends Fragment {

    Button trackOrder;
    Button backToHome;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trackOrder = view.findViewById(R.id.trackOrderButton);
        backToHome = view.findViewById(R.id.backToHomeButton);

        backToHome.setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_confirmationFragment_to_foodItemFragment));

        trackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_confirmationFragment_to_orderFragment);
            }
        });

    }
}