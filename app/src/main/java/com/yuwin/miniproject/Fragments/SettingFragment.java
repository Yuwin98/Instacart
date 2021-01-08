package com.yuwin.miniproject.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.yuwin.miniproject.R;

import static android.content.Context.MODE_PRIVATE;


public class SettingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedPreferences = requireActivity().getSharedPreferences("com.yuwin.miniproject", MODE_PRIVATE);

        Spinner languageSpinner =  view.findViewById(R.id.languageSpinner);
        Spinner locationSpinner = view.findViewById(R.id.locationSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.languages, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(requireContext(),
                R.array.locations, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(adapter);
        locationSpinner.setAdapter(adapter2);

        languageSpinner.setSelection(mSharedPreferences.getInt("language",0));
        locationSpinner.setSelection(mSharedPreferences.getInt("location",0));

        languageSpinner.setOnItemSelectedListener(this);
        locationSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.languageSpinner){
            mSharedPreferences.edit().putInt("language", position).apply();
        }else if(spinner.getId() == R.id.locationSpinner) {
            mSharedPreferences.edit().putInt("location", position).apply();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}