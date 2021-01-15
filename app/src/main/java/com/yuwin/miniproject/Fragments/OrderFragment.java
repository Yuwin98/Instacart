package com.yuwin.miniproject.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.todkars.shimmer.ShimmerRecyclerView;
import com.yuwin.miniproject.Models.OrderModel;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.RecyclerViews.Adapters.OrderAdapter;
import com.yuwin.miniproject.utility.OrderProgress;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {

    ShimmerRecyclerView orderShimmer;
    OrderAdapter mOrderAdapter;

    FirebaseUser mUser;
    FirebaseFirestore fs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        fs = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderShimmer = view.findViewById(R.id.orderShimmerRecyclerView);
        orderShimmer.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        showShimmer();
        mOrderAdapter = new OrderAdapter();
        getData();
    }

    private void getData() {
        List<OrderModel> data = new ArrayList<>();

        fs.collection("orders")
                .document(mUser.getUid())
                .collection("order_list")
                .get().addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document: task.getResult()) {
                            data.add(
                                    new OrderModel(
                                            document.get("imgurl").toString(),
                                            document.get("mealname").toString(),
                                            document.get("total").toString(),
                                            document.get("stars").toString(),
                                            document.get("order_placed_at").toString(),
                                            OrderProgress.valueOf(document.get("progress").toString())
                                    )
                            );
                        }
                    }else {
                        Log.d("order_retrieval", "Orders Couldn't be retrieved");
                    }
                    mOrderAdapter.setData(data);
                    orderShimmer.setAdapter(mOrderAdapter);
                    hideShimmer();

                });

    }

    private void showShimmer() {
        orderShimmer.showShimmer();
    }

    private void hideShimmer() {
        orderShimmer.hideShimmer();
    }
}