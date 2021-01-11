package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.todkars.shimmer.ShimmerRecyclerView;
import com.yuwin.miniproject.Models.OptionsModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Adapters.AvailableMealAdapter;
import com.yuwin.miniproject.RecyclerViews.Adapters.OptionsAdapter;

import java.util.ArrayList;
import java.util.List;


public class UpsellFragment extends Fragment {

    TextView mMealPriceTextView;
    Button checkoutButton;

    ShimmerRecyclerView mUpSellRecyclerView;
    OptionsAdapter mUpsellAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upsell, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String mealPriceText = UpsellFragmentArgs.fromBundle(getArguments()).getPrice();

        checkoutButton = view.findViewById(R.id.checkoutButton);

        mMealPriceTextView = view.findViewById(R.id.upsellMealPrice);
        mUpSellRecyclerView = view.findViewById(R.id.upsellRecyclerView);
        showShimmer();
        mUpSellRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        String cleanPrice = mealPriceText.replace("$", "");
        float mealPrice = Float.parseFloat(cleanPrice);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpsellFragmentDirections.UpsellToCheckout action = UpsellFragmentDirections.UpsellToCheckout(mealPriceText);
                Navigation.findNavController(v)
                        .navigate(action);
            }
        });

        mMealPriceTextView.setText(mealPriceText);
        mUpsellAdapter = new OptionsAdapter(getActivity().getApplicationContext(), mealPrice, mMealPriceTextView);
        getData();

    }

    private void getData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<OptionsModel> data = new ArrayList<>();
        db.collection("upsell")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            data.add(new OptionsModel(
                                    document.getData().get("img").toString(),
                                    document.getData().get("name").toString(),
                                    document.getData().get("price").toString()
                            ));
                        }

                    } else {
                        Log.d("options", "Error Getting documents", task.getException());
                    }
                    mUpsellAdapter.setData(data);
                    mUpSellRecyclerView.setAdapter(mUpsellAdapter);
                    hideShimmer();
                });
    }

    private void showShimmer() {
        mUpSellRecyclerView.showShimmer();
    }

    private void hideShimmer() {
        mUpSellRecyclerView.hideShimmer();
    }
}