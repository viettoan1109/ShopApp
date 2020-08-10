package com.actvn.shopapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.SearchActivity;
import com.actvn.shopapp.api.adapter.StoreAdapter;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Products;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.utils.ConstApp;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StoreFragment extends Fragment {
    private CardView cardViewMeat;
    private CardView cardViewFish;
    private CardView cardViewFruits;
    private CardView cardViewVegetable;
    private ViewFlipper viewFlipper;
    private View view;
    private StoreAdapter storeAdapter;
    private UserService userService;
    private RecyclerView recyclerView;
    private List<Data> datas = new ArrayList<>();
    private Products products;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_store, container, false);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.PRODUCT_URL)
                .client(client.build()).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);
        denzcokunView();
        initViewss();
        loadItem();

        return view;
    }

    private void denzcokunView(){
        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-6.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/reno4-chung-800-300-800x300.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-9.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/a51-71-800-300-800x300.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-4.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-8.png"));

        imageSlider.setImageList(slideModels,false);
    }

    private void initViewss() {
        storeAdapter = new StoreAdapter(datas, getContext());

        /*viewFlipper = view.findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);*/

        cardViewMeat = view.findViewById(R.id.cardviewMeat);
        cardViewFish = view.findViewById(R.id.cardviewFish);
        cardViewFruits = view.findViewById(R.id.cardviewFruits);
        cardViewVegetable = view.findViewById(R.id.cardviewVegetables);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(storeAdapter);



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

    private void loadItem() {
        Call<Products> call = userService.getProducts();

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful() && response.body().getData() != null) {
                    if (!datas.isEmpty()) {
                        datas.clear();
                    }
                    datas = response.body().getData();
                    storeAdapter = new StoreAdapter(datas, getContext());
                    recyclerView.setAdapter(storeAdapter);
                    storeAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getContext(), "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }

        });
    }
}
