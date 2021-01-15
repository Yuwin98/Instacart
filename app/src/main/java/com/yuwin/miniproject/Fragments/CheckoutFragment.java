package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yuwin.miniproject.DB.AppDatabase;
import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.Models.AvailableMeal;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.utility.OrderProgress;
import com.yuwin.miniproject.utility.Utility;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


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

    FirebaseFirestore fs;
    FirebaseUser mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fs = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }

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
        AvailableMeal meal = CheckoutFragmentArgs.fromBundle(getArguments()).getMeal();
        paymentCreditCard = view.findViewById(R.id.paymentCreditCard);
        addNewCreditCard = view.findViewById(R.id.addNewCard);

        finishPurchaseButton = view.findViewById(R.id.finishPurchaseButton);
        finishPurchaseButton.setEnabled(true);

        paymentCardType = view.findViewById(R.id.paymentCardType);
        paymentCardNumber = view.findViewById(R.id.paymentCardNumber);
        paymentCardOwner = view.findViewById(R.id.paymentCardOwner);
        paymentCardExpiryDate = view.findViewById(R.id.paymentCardExpiration);

        if(appDB.cardInfoDao().count() == 0) {
            finishPurchaseButton.setVisibility(View.INVISIBLE);
            paymentCreditCard.setVisibility(View.INVISIBLE);
            addNewCreditCard.setVisibility(View.VISIBLE);
        }else {
            CardEntity cardEntity = appDB.cardInfoDao().getUserCards();
            Utility.setImageIcon(paymentCardType, cardEntity);
            paymentCardNumber.setText(cardEntity.getCardNumber());
            paymentCardOwner.setText(cardEntity.getCardOwner());
            paymentCardExpiryDate.setText(cardEntity.getCardExpirationDate());
            paymentCreditCard.setVisibility(View.VISIBLE);
            finishPurchaseButton.setVisibility(View.VISIBLE);
            addNewCreditCard.setVisibility(View.INVISIBLE);
        }

        addNewCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_checkoutFragment_to_cardFragment);
            }
        });




        finishPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishPurchaseButton.setEnabled(false);
                saveOrder(meal, price, "", v);
            }
        });

        totalPriceTextView = view.findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(price);
    }

    private void saveOrder(AvailableMeal meal, String totalPrice, String stars, View v) {

        String time = ZonedDateTime.now(
                ZoneId.of("Asia/Colombo")
        ).format(
                DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")
        );
        Log.d("order", "Order Save Started");
        Map<String, String> data = new HashMap<>();
        data.put("imgurl", meal.getImageUrl());
        data.put("mealname", meal.getMealName());
        data.put("total", totalPrice);
        data.put("progress", OrderProgress.PLACED.toString());
        data.put("order_placed_at", time);


        if(!stars.isEmpty()) {
            data.put("stars", stars);
        }else {
            data.put("stars", "");
        }


        fs.collection("orders").document(mUser.getUid())
                .collection("order_list")
                .document()
                .set(data)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext()
                                , "Order could not be placed at this time",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Navigation.findNavController(v)
                        .navigate(R.id.action_checkoutFragment_to_confirmationFragment);
            }
        });

    }
}