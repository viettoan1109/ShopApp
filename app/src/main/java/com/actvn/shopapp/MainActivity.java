package com.actvn.shopapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.actvn.shopapp.fragment.CartFragment;
import com.actvn.shopapp.fragment.FavouriteFragment;
import com.actvn.shopapp.fragment.ProfileFragment;
import com.actvn.shopapp.fragment.StoreFragment;
import com.actvn.shopapp.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private ViewPager vpgFace;
    private TabLayout tabFace;

    private StoreFragment storeFragment;
    private CartFragment cartFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        actionToolbar();
        addControl();
        setIconTablayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnSearch:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.mnNotify:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        vpgFace = findViewById(R.id.vpgFace);
        tabFace = findViewById(R.id.tabFace);
        toolbar = findViewById(R.id.toolbarMain);
        drawerLayout = findViewById(R.id.drawerLayout);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_dehaze_24); // Set Icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void addControl() {
        tabFace.setupWithViewPager(vpgFace);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

        vpgFace.setAdapter(viewPagerAdapter);


        vpgFace.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabFace));
        tabFace.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vpgFace));

        vpgFace.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        toolbar.setTitle("Store");
                        tabFace.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);

                        break;
                    case  1:
                        toolbar.setTitle("Cart");
                        tabFace.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);

                        break;
                    case 2:
                        toolbar.setTitle("Profile");
                        tabFace.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(2).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setIconTablayout() {
        tabFace.getTabAt(0).setIcon(R.drawable.ic_store_24);
        tabFace.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabFace.getTabAt(1).setIcon(R.drawable.ic_cart_24);
        tabFace.getTabAt(2).setIcon(R.drawable.ic_account_circle_24);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new StoreFragment();
                    break;

                case 1:
                    fragment = new CartFragment();
                    break;

                case 2:
                    fragment = new ProfileFragment();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}