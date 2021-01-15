package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuwin.miniproject.DB.AppDatabase;
import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.utility.Utility;


public class CheckoutFragment extends Fragment {

    Button finishPurchaseButton;
    TextView totalPriceTextView;

    CardView addNewCreditCard;
    CardView paymentCreditCard;

    ImageView paymentCardType;
    TextView paymentCardNumber;
    TextView paymentCardOwner;
    TextView paymentCardExpiryDate;

    AppDatabase appDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDB = AppDatabase.getInstance(requireContext());
        String price = CheckoutFragmentArgs.fromBundle(getArguments()).getTotal();
        paymentCreditCard = view.findViewById(R.id.paymentCreditCard);
        addNewCreditCard = view.findViewById(R.id.addNewCard);

        paymentCardType = view.findViewById(R.id.paymentCardType);
        paymentCardNumber = view.findViewById(R.id.paymentCardNumber);
        paymentCardOwner = view.findViewById(R.id.paymentCardOwner);
        paymentCardExpiryDate = view.findViewById(R.id.paymentCardExpiration);

        if(appDB.cardInfoDao().count() == 0) {
            paymentCreditCard.setVisibility(View.INVISIBLE);
            addNewCreditCard.setVisibility(View.VISIBLE);
        }else {
            CardEntity cardEntity = appDB.cardInfoDao().getUserCards();
            Utility.setImageIcon(paymentCardType, cardEntity);
            paymentCardNumber.setText(cardEntity.getCardNumber());
            paymentCardOwner.setText(cardEntity.getCardOwner());
            paymentCardExpiryDate.setText(cardEntity.getCardExpirationDate());
            paymentCreditCard.setVisibility(View.VISIBLE);
            addNewCreditCard.setVisibility(View.INVISIBLE);
        }

        addNewCreditCard.setOnClickListener(new View.OnClickListener() {
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