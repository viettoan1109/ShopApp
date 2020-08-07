package com.actvn.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbarSearch;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        actionToolbar();

    }

    private void initView() {
        toolbarSearch = findViewById(R.id.toolbarSearch);
        searchView =  findViewById(R.id.searchView);
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
}