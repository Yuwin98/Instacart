package com.yuwin.miniproject.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {

    private static final int READ_EXTERNAL_STORAGE = 200;
    private static final int PICK_IMAGE = 1;
    private String userID = "";
    private String realPath = "";

    EditText emailEditText;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    TextView profileUsername;

    EditText userFirstName;
    EditText userLastName;

    ImageView userImageView;

    Button profileSaveButton;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userID = mUser.getUid();
        Log.d("user", userID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileSaveButton = view.findViewById(R.id.profileSaveButton);
        profileSaveButton.setEnabled(false);
        profileUsername = view.findViewById(R.id.profileFullName);
        userFirstName = view.findViewById(R.id.userFirstNameEditText);
        userLastName = view.findViewById(R.id.userLastNameEditText);
        userFirstName.addTextChangedListener(userNameTextWatcher);
        userLastName.addTextChangedListener(userNameTextWatcher);
        profileSaveButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.profile_button));

        emailEditText = view.findViewById(R.id.userEmailEditText);

        userImageView = view.findViewById(R.id.userImageView);
        userImageView.setOnClickListener(v -> pickUserImage());

        profileSaveButton.setOnClickListener(v -> updateUserProfile());


        fillUserDetails();
    }

    TextWatcher userNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(validateName(s.toString())){
                profileSaveButton.setEnabled(true);
                profileSaveButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.profile_button_active));
            }else {
                profileSaveButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.profile_button));
                profileSaveButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void updateUserProfile() {
        profileSaveButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.profile_button));
        profileSaveButton.setEnabled(false);
        String name = userFirstName.getText().toString() + " " + userLastName.getText().toString();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        mUser.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    Toast.makeText(requireContext(), "User Profile Updated", Toast.LENGTH_LONG).show();
                });
    }

    private void updateUserProfileImage(Uri uri) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        mUser.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    Log.d("upload", "User profile update successful");
                    Toast.makeText(requireContext(), "User Image Uploaded", Toast.LENGTH_LONG).show();
                });
    }

    private void fillUserDetails() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            emailEditText.setText(user.getEmail());
            processUsername(user);

            if(user.getPhotoUrl() != null) {
                Glide.with(requireContext()).asBitmap().load(user.getPhotoUrl()).into(userImageView);
            }
        }
    }

    private void processUsername(FirebaseUser user) {
        String username;
        username = user.getDisplayName();
        if(!username.isEmpty()) {
            profileUsername.setVisibility(View.VISIBLE);
            profileUsername.setText(username);
            String[] userNames = username.split(" ");
            if(!userNames[0].isEmpty()) {
                userFirstName.setText(userNames[0]);
            }

        }
    }

    private boolean validateName(String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    private void pickUserImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && data != null) {
                final Uri image = data.getData();
                Glide.with(requireContext()).asBitmap().load(image).into(userImageView);
                Log.d("upload", "About to Start photo Upload");
                realPath = Utility.getRealPathFromURI(requireContext(), image);
                Log.d("upload", realPath);
                checkPermission(realPath);
        }
    }

    private void checkPermission(String realPath) {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            uploadProfileImage(realPath);

        }else {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}
            , READ_EXTERNAL_STORAGE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                uploadProfileImage(realPath);
            }
        }
    }

    private void uploadProfileImage(String realPath) {
        Log.d("upload", "Upload Method Begin");
        String imgName = userID + ".jpg";
        String fullImagePath = "users/" + imgName;
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference storageImageRef = storageRef.child(fullImagePath);

//        userImageView.setDrawingCacheEnabled(true);
//        userImageView.buildDrawingCache();
//
//        Bitmap bitmap = ((BitmapDrawable) userImageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] data = baos.toByteArray();
        try {
            Log.d("upload", "Preparing Uri");
            File file = new File(realPath);
            Log.d("upload", file.getAbsolutePath());

            InputStream stream = new FileInputStream(file);
            UploadTask uploadTask = storageImageRef.putStream(stream);
            uploadTask
                    .addOnFailureListener(e -> {
                        Toast.makeText(requireContext(), "Image Upload Failed", Toast.LENGTH_SHORT).show();
                    })
                    .addOnSuccessListener(taskSnapshot -> {
                        Log.d("upload", "Upload Successful");
                        storageImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("upload", "Updating user profile");
                                updateUserProfileImage(uri);
                            }
                        });
                        Toast.makeText(requireContext(), "Image Upload Successful", Toast.LENGTH_SHORT).show();
                    });
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("upload", "File not found");
        }

    }
}