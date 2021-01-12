package com.yuwin.miniproject.Fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.provider.CalendarContract;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yuwin.miniproject.DB.AppDatabase;
import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.R;


public class CardFragment extends Fragment {

    FloatingActionButton addCardFab;
    ActionMode mActionMode;

    TextView userCardNumber;
    TextView cardOwnerName;
    TextView cardExpiryDate;

    ImageView creditCardType;

    CardView userCardView;
    AppDatabase appDB;
    CardEntity cardEntity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addCardFab = view.findViewById(R.id.addCardFab);
        creditCardType = view.findViewById(R.id.creditCardType);
        userCardView = view.findViewById(R.id.userCreditCard);
        userCardNumber = view.findViewById(R.id.userCardNumber);
        cardOwnerName = view.findViewById(R.id.cardOwnerName);
        cardExpiryDate = view.findViewById(R.id.cardExpiryDate);
        appDB = AppDatabase.getInstance(requireContext());

        if(appDB.cardInfoDao().count() == 1) {
            cardEntity = appDB.cardInfoDao().getUserCards();
            userCardView.setVisibility(View.VISIBLE);
            setImageIcon(creditCardType);
            userCardNumber.setText(cardEntity.getCardNumber());
            cardOwnerName.setText(cardEntity.getCardOwner());
            cardExpiryDate.setText(cardEntity.getCardExpirationDate());
        }



        addCardFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_cardFragment_to_addCardFragment);
            }
        });

    }

    private void setImageIcon(ImageView cardView) {
        char cardNumber = cardEntity.cardNumber.charAt(0);
        if(cardNumber == '4') {
            cardView.setImageResource(R.drawable.ic_visa);
        }else if(cardNumber == '5') {
            cardView.setImageResource(R.drawable.ic_mastercard);
        }
    }
}