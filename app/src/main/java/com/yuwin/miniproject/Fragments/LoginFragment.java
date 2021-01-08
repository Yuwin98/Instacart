package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.yuwin.miniproject.R;


public class LoginFragment extends Fragment {

    private final VerifyEmail mVerifyEmail = new VerifyEmail();
    private final VerifyPassword mVerifyPassword = new VerifyPassword();
    FirebaseAuth mAuth;

    Button signIn;
    TextView signUpTextView;

    TextView emailErrorText;
    TextView passwordErrorText;

    EditText emailEditText;
    EditText passwordEditText;

    boolean isValidEmail = false;
    boolean isValidPassword = false;

    StringBuilder sb_pass = new StringBuilder();
    StringBuilder sb_email = new StringBuilder();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mAuth = FirebaseAuth.getInstance();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.bootomNavigationView);
        navigationView.setVisibility(View.GONE);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        emailEditText = view.findViewById(R.id.signUpEmailEditText);
        emailErrorText = view.findViewById(R.id.emailErrorField);
        passwordEditText = view.findViewById(R.id.signUpPasswordEditText);
        passwordErrorText = view.findViewById(R.id.passwordErrorField);


        passwordEditText.addTextChangedListener(passwordTextWatcher);
        emailEditText.addTextChangedListener(emailTextWatcher);

        signUpTextView = view.findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(view2 -> Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_loginFragment_to_signUpFragment));

        signIn = view.findViewById(R.id.signUpButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                isValidEmail = mVerifyEmail.verifyEmailField(email, sb_email, emailErrorText);
                isValidPassword = mVerifyPassword.verifyPasswordField(password, sb_pass, passwordErrorText);

                if(isValidEmail && isValidPassword) {
                    LoginFragmentDirections.ActionLoginFragmentToIntermediateLoadingFragment action = LoginFragmentDirections.actionLoginFragmentToIntermediateLoadingFragment(email, password);
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(action);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           isValidEmail = mVerifyEmail.verifyEmailField(charSequence, sb_email, emailErrorText);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            emailErrorText.setText(sb_email.toString());
        }
    };

    TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           isValidPassword = mVerifyPassword.verifyPasswordField(charSequence, sb_pass, passwordErrorText);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            passwordErrorText.setText(sb_pass.toString());
        }
    };







}