package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yuwin.miniproject.R;


public class CheckoutFragment extends Fragment {

    Button finishPurchaseButton;
    TextView totalPriceTextView;

    CardView addCardCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String price = CheckoutFragmentArgs.fromBundle(getArguments()).getTotal();

        addCardCardView = view.findViewById(R.id.addNewCard);
        addCardCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_checkoutFragment_to_cardFragment);
            }
        });

        finishPurchaseButton = view.findViewById(R.id.finishPurchaseButton);
        finishPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.action_checkoutFragment_to_confirmationFragment);
            }
        });

        totalPriceTextView = view.findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(price);
    }
}