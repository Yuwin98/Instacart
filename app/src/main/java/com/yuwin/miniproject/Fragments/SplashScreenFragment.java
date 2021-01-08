package com.yuwin.miniproject.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yuwin.miniproject.R;

import static android.content.Context.MODE_PRIVATE;


public class SplashScreenFragment extends Fragment {


    SharedPreferences mSharedPreferences = null;
    Handler mHandler = new Handler(Looper.getMainLooper());
    FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        mHandler.postDelayed(() -> {
            if(mSharedPreferences.getBoolean("appHasRunBefore",false)){


                        if(mAuth.getCurrentUser() != null) {
                            alreadySignedUp(mAuth.getCurrentUser());
                        }else  {

                            Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
                                    .navigate(R.id.action_splashScreenFragment_to_loginFragment);
                        }

//                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
//                        .navigate(R.id.action_splashScreenFragment_to_onBoardingFragment);
            }else {
                mSharedPreferences.edit().putBoolean("appHasRunBefore",true).apply();
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
                        .navigate(R.id.action_splashScreenFragment_to_onBoardingFragment);
            }
        }, 2000);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.bootomNavigationView);
        navigationView.setVisibility(View.GONE);
        mSharedPreferences = requireActivity().getSharedPreferences("com.yuwin.miniproject", MODE_PRIVATE);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private void alreadySignedUp(FirebaseUser user) {
        if(user != null) {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_splashScreenFragment_to_foodItemFragment);
        }
    }



}