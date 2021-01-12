package com.yuwin.miniproject.Fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.type.DateTime;
import com.yuwin.miniproject.DB.AppDatabase;
import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddCardFragment extends Fragment {

    FloatingActionButton cardDetailConfirmFAB;

    EditText cardOwnerEditText;
    EditText cardNumberEditText;
    EditText cardExpiryMonthEditText;
    EditText cardExpiryYearEditText;
    EditText cardCVVEditText;

    AppDatabase appDB;
    CardEntity cardData;

    String cardNumber;
    String cardMonth;
    String cardYear;
    String cardCvv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDB = AppDatabase.getInstance(requireContext());
        cardDetailConfirmFAB = view.findViewById(R.id.cardDetailsConfirmFab);
        cardDetailConfirmFAB.setEnabled(false);


        cardOwnerEditText = view.findViewById(R.id.cardOwnerEditText);
        cardNumberEditText = view.findViewById(R.id.cardNumberEditText);
        cardExpiryMonthEditText = view.findViewById(R.id.cardExpiryMonth);
        cardExpiryYearEditText = view.findViewById(R.id.cardExpiryYear);
        cardCVVEditText = view.findViewById(R.id.cardExpiryCVV);

        cardExpiryYearEditText.addTextChangedListener(cardDetailsTextWatcher);
        cardExpiryMonthEditText.addTextChangedListener(cardDetailsTextWatcher);
        cardCVVEditText.addTextChangedListener(cardDetailsTextWatcher);
        cardNumberEditText.addTextChangedListener(cardDetailsTextWatcher);


        cardDetailConfirmFAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cardData = new CardEntity(
                        cardOwnerEditText.getText().toString(),
                        cardNumber,
                        cardMonth,
                        cardYear,
                        cardCvv
                );
                appDB.cardInfoDao().insertCardData(cardData);
                Navigation.findNavController(v).navigate(R.id.action_addCardFragment_to_cardFragment);
            }
        });

    }

    TextWatcher cardDetailsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(validateCardDetails()) {
                cardDetailConfirmFAB.setEnabled(true);
                cardDetailConfirmFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.Green)));
            }else {
                cardDetailConfirmFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.profileTextPrimary)));
                cardDetailConfirmFAB.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private boolean validateCardDetails() {

         cardNumber = cardNumberEditText.getText().toString();
         cardMonth = cardExpiryMonthEditText.getText().toString();
         cardYear = cardExpiryYearEditText.getText().toString();
         cardCvv = cardCVVEditText.getText().toString();

         if(cardMonth.length() == 1){
             cardMonth = "0" + cardMonth;
         }

        return validateCardNumber(cardNumber) &&
                validateCardMonth(cardMonth) &&
                validateCardYear(cardYear) &&
                validateCardCvv(cardCvv);
    }

    private boolean validateCardCvv(String cardCvv) {
        boolean isCardCvvValid = true;

        if(cardCvv.length() != 3) {
            isCardCvvValid = false;
        }

        return isCardCvvValid;
    }
    
    private boolean validateCardYear(String cardYear) {
        boolean isYearValid = true;
        int year = 0;

        if(cardYear.length() != 4) {
            isYearValid = false;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        try {
             year = Integer.parseInt(cardYear);
        }catch (NumberFormatException e) {
            return  false;
        }

        if(year < currentYear) {
            isYearValid = false;
        }
        
        return isYearValid;
    }
    
    private boolean validateCardMonth(String cardMonth) {
        boolean isMonthValid = true;
        int month = 0;

        try {
            month = Integer.parseInt(cardMonth);
        }catch (NumberFormatException e) {
            return false;
        }

        if(month <= 0 || month > 12) {
            isMonthValid = false;
        }

        return isMonthValid;
    }

    private boolean validateCardNumber(String cardNumber) {
        boolean isNumberValid = true;

        String regex = "^[0-9]{13,16}";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(cardNumber);
        boolean isAMatch = match.find();

        if(!cardNumber.isEmpty()){
            isNumberValid = false;
            if (cardNumber.charAt(0) == '4' || cardNumber.charAt(0) == '5'){
                isNumberValid = true;
            }
        }



        if(!isAMatch) {
            isNumberValid = false;
        }

        if(TextUtils.isEmpty(cardNumber)) {
            isNumberValid = false;
        }


        return isNumberValid;
    }

}