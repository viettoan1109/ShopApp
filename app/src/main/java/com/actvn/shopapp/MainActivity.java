package com.actvn.shopapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.actvn.shopapp.fragment.CartFragment;
import com.actvn.shopapp.fragment.ContentFragment;
import com.actvn.shopapp.fragment.HelpFragment;
import com.actvn.shopapp.fragment.ProfileFragment;
import com.actvn.shopapp.fragment.QuestionFragment;
import com.actvn.shopapp.fragment.StoreFragment;
import com.actvn.shopapp.fragment.StoreLocatorFragment;
import com.actvn.shopapp.fragment.TermsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager vpgFace;
    private TabLayout tabFace;
    private QuestionFragment questionFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //displaySelectedScreen(R.id.nav_locator);

        /*fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.vpgFace,new ContentFragment());
        fragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.nav_locator);
        navigationView.setCheckedItem(R.id.nav_help);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setCheckedItem(R.id.nav_term);
        navigationView.setCheckedItem(R.id.nav_question);
        getSupportFragmentManager().beginTransaction().replace(R.id.vpgFace, new StoreFragment()).commit();*/
        /*Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_locator).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this,StoreLocatorFragment.class);
                startActivity(intent);
                return false;
            }
        });*/

        //getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new StoreFragment()).commit();

    }

    // Toolbar
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

    // Viewpager
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
                switch (position) {
                    case 0:
                        toolbar.setTitle("Store");
                        tabFace.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);
                        tabFace.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#1F915F"), PorterDuff.Mode.SRC_IN);

                        break;
                    case 1:
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


    // Viewpager Tablayout
    private void setIconTablayout() {
        tabFace.getTabAt(0).setIcon(R.drawable.ic_store_24);
        tabFace.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabFace.getTabAt(1).setIcon(R.drawable.ic_cart_24);
        tabFace.getTabAt(2).setIcon(R.drawable.ic_account_circle_24);

    }

   // Navigation
   /* private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                break;
            case R.id.nav_locator:
                fragment = new StoreLocatorFragment();
                break;
            case R.id.nav_term:
                fragment = new TermsFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
    }*/

    // Set onclick on navigationview
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       /* switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(),"Hellllllo",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.vpgFace, new StoreFragment()).commit();
                break;
            case R.id.nav_locator:
                getSupportFragmentManager().beginTransaction().replace(R.id.vpgFace, new StoreLocatorFragment()).commit();
                break;
            case R.id.nav_term:
                getSupportFragmentManager().beginTransaction().replace(R.id.vpgFace, new TermsFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.vpgFace, new HelpFragment()).commit();
                break;
            case R.id.nav_question:
                //getSupportFragmentManager().beginTransaction().add(R.id.nav_question,questionFragment,"Fragment").commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.vpgFace, new QuestionFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);*/
        //displaySelectedScreen(menuItem.getItemId());
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
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