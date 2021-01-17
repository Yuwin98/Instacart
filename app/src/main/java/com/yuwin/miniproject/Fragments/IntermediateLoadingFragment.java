package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yuwin.miniproject.R;


public class IntermediateLoadingFragment extends Fragment {

    FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intermediate_loading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String email = IntermediateLoadingFragmentArgs.fromBundle(getArguments()).getEmail();
        String password = IntermediateLoadingFragmentArgs.fromBundle(getArguments()).getPassword();

        singIn(email    , password);

    }



    private void singIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {

                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_intermediateLoadingFragment_to_foodItemFragment);
                    } else {
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_intermediateLoadingFragment_to_loginFragment);
                        Toast.makeText(requireActivity(), "Username or Password Incorrect", Toast.LENGTH_LONG).show();
                    }
                });
    }
}