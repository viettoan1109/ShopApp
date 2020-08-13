package com.actvn.shopapp.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.SearchManager;
import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.graphics.Color;
=======
>>>>>>> 771aa5f0c751da573f50668a1700ba9ee39563e2
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import com.actvn.shopapp.search.SearchSuggestions;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Products;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.search.SearchAdapter;
import com.actvn.shopapp.register.RegisterActivity;
import com.actvn.shopapp.utils.ConstApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private UserService userService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Data> datas = new ArrayList<>();
    private List<Products> mProducts;
    private SearchAdapter searchAdapter;

    private Toolbar toolbarSearch;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


<<<<<<< HEAD
=======

>>>>>>> 771aa5f0c751da573f50668a1700ba9ee39563e2
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);


        initView();
        actionToolbar();
        search();
    }

    private void initView() {

        searchAdapter = new SearchAdapter(this, datas);

        recyclerView = findViewById(R.id.recyclerViewSearch);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
<<<<<<< HEAD
        //recyclerView.setNestedScrollingEnabled(true);
=======
       //recyclerView.setNestedScrollingEnabled(true);
>>>>>>> 771aa5f0c751da573f50668a1700ba9ee39563e2
        recyclerView.setAdapter(searchAdapter);

        toolbarSearch = findViewById(R.id.toolbarSearch);
        searchView = findViewById(R.id.searchView);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbarSearch.setNavigationIcon(R.drawable.ic_arrow_back_24);
        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void search() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
<<<<<<< HEAD

        /*Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(
                    this,
                    SearchSuggestions.AUTHORITY,
                    SearchSuggestions.MODE);

            searchRecentSuggestions.saveRecentQuery(query, null);
        }*/

=======
        searchView.setQueryHint("Search Here!!!");
>>>>>>> 771aa5f0c751da573f50668a1700ba9ee39563e2
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 1) {
                    loadJson(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadJson(newText);
                return false;
            }
        });

    }

    private void loadJson(final String keyword) {

        /*userService.getProducts().enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful() && response.body().getData() != null){
                    List<Data> result = response.body().getData();
                    datas.clear();
                    datas.addAll(result);
                    searchAdapter = new SearchAdapter(SearchActivity.this, datas);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });*/
        Call<Products> call;
<<<<<<< HEAD
        if (keyword.length() > 0) {
            call = userService.getSearchProducts(keyword);
            //Toast.makeText(SearchActivity.this, keyword, Toast.LENGTH_SHORT).show();

        } else {
            call = userService.getProducts();
=======
        if (keyword.length() > 0){
            call = userService.getSearchProducts(keyword);
            Toast.makeText(SearchActivity.this, keyword, Toast.LENGTH_SHORT).show();

        } else {
          call = userService.getProducts();
>>>>>>> 771aa5f0c751da573f50668a1700ba9ee39563e2
            Toast.makeText(SearchActivity.this, "No Result", Toast.LENGTH_SHORT).show();

        }

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
<<<<<<< HEAD
                if (response.isSuccessful() && response.body().getData() != null) {
                    if (!datas.isEmpty()) {
=======
                if (response.isSuccessful() && response.body().getData() != null){
                    if (!datas.isEmpty()){
>>>>>>> 771aa5f0c751da573f50668a1700ba9ee39563e2
                        datas.clear();
                    }
                    datas = response.body().getData();
                    searchAdapter = new SearchAdapter(SearchActivity.this, datas);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(SearchActivity.this, "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }

        });
    }
}