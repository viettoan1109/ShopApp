package com.actvn.shopapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.adapter.StoreAdapter;


public class StoreFragment extends Fragment {
    private CardView cardViewMeat;
    private CardView cardViewFish;
    private CardView cardViewFruits;
    private CardView cardViewVegetable;
    private ViewFlipper viewFlipper;
    private View view;
    private StoreAdapter storeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store, container, false);
        initViewss();

        return view;
    }

    private void initViewss(){
        storeAdapter = new StoreAdapter();

        viewFlipper = view.findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        cardViewMeat = view.findViewById(R.id.cardviewMeat);
        cardViewFish = view.findViewById(R.id.cardviewFish);
        cardViewFruits = view.findViewById(R.id.cardviewFruits);
        cardViewVegetable = view.findViewById(R.id.cardviewVegetables);

        cardViewMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        cardViewFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardViewVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void GetNewItem() {

    }
}
