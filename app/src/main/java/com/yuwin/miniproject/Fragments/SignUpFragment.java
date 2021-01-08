package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.yuwin.miniproject.R;


public class SignUpFragment extends Fragment {

    TextView signInTextView;

    TextView emailErrorText;
    TextView passwordErrorText;

    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordConfirmEditText;

    boolean isValidEmail = false;
    boolean isValidPassword = false;

    Button signUpBtn;

    private FirebaseAuth mAuth;
    private VerifyEmail mVerifyEmail = new VerifyEmail();
    private VerifyPassword mVerifyPassword = new VerifyPassword();

    StringBuilder sb_email = new StringBuilder();
    StringBuilder sb_pass = new StringBuilder();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText = view.findViewById(R.id.signUpEmailEditText);
        emailErrorText = view.findViewById(R.id.emailErrorText);

        passwordEditText = view.findViewById(R.id.signUpPasswordEditText);
        passwordConfirmEditText = view.findViewById(R.id.signUpPasswordConfirmEditText);
        passwordErrorText = view.findViewById(R.id.passwordErrorText);

        signUpBtn = view.findViewById(R.id.signUpButton);

        emailEditText.addTextChangedListener(emailTextWatcher);
        passwordEditText.addTextChangedListener(passwordTextWatcher);
        passwordConfirmEditText.addTextChangedListener(passwordConfirmTextWatcher);

        signInTextView = view.findViewById(R.id.signInTextVIew);
        signInTextView.setOnClickListener(view1 -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_signUpFragment_to_loginFragment);
        });





        signUpBtn.setOnClickListener(v -> {

            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = passwordConfirmEditText.getText().toString();

            boolean isEmailValid = mVerifyEmail.verifyEmailField(email, sb_email, emailErrorText);
            boolean isPasswordValid = mVerifyPassword.verifyPasswordField(password,confirmPassword, sb_pass, passwordErrorText);

            Log.d("user", "Email Valid-" + isEmailValid);
            Log.d("user", "Password Valid-" + isPasswordValid);
            if (isEmailValid && isPasswordValid) {
                Log.d("user", "User Signing Up...");
                singUpUser(email, password);
            }
        });


    }

    private void singUpUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        SignUpFragmentDirections.ActionSignUpFragmentToIntermediateLoadingFragment action = SignUpFragmentDirections.actionSignUpFragmentToIntermediateLoadingFragment(email, password);
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                                .navigate(action);
                    } else {
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.action_intermediateLoadingFragment_to_signUpFragment);
                    }
                });

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

    TextWatcher passwordConfirmTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String password = passwordEditText.getText().toString();
            isValidPassword = mVerifyPassword.verifyPasswordField(password,charSequence, sb_pass, passwordErrorText);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            passwordErrorText.setText(sb_pass.toString());
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
}