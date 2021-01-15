package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yuwin.miniproject.DB.AppDatabase;
import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.utility.Utility;


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
            Utility.setImageIcon(creditCardType, cardEntity);
            userCardNumber.setText(cardEntity.getCardNumber());
            cardOwnerName.setText(cardEntity.getCardOwner());
            cardExpiryDate.setText(cardEntity.getCardExpirationDate());
        }

        userCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if(mActionMode != null){
                    return false;
                }
                mActionMode = v.startActionMode(mActionModeCallback);

                return true;
            }
        });

        addCardFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_cardFragment_to_addCardFragment);
            }
        });

    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete_card:{
                    userCardView.setVisibility(View.INVISIBLE);
                    appDB.cardInfoDao().deleteCard(cardEntity);
                    mode.finish();
                    return true;
                }
                default:return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };


}