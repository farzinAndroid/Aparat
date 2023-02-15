package com.farzin.aparat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.farzin.aparat.Utils.AppConfig;
import com.farzin.aparat.activities.LoginActivity;
import com.farzin.aparat.activities.SearchActivity;
import com.farzin.aparat.activities.SettingsActivity;
import com.farzin.aparat.adapter.HomeAdapter;
import com.farzin.aparat.databinding.ActivityMainBinding;
import com.farzin.aparat.fragments.CategoryFragment;
import com.farzin.aparat.fragments.FavoriteFragment;
import com.farzin.aparat.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivityMainBinding binding;
    private AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();
        appConfig = new AppConfig(getApplicationContext());
        callingFragments();
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,binding.drawer,binding.toolbar,R.string.open,R.string.close);
        toggle.syncState();



        binding.navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_login:

                        if (appConfig.getUserid() > 0){

                            Toast.makeText(MainActivity.this, "You are already logged", Toast.LENGTH_SHORT).show();

                        }else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            binding.drawer.close();
                        }
                        break;

                    case R.id.nav_home:
                        binding.viewPager.setCurrentItem(0);
                        changePageTitle(0);
                        binding.drawer.close();
                        break;

                    case R.id.nav_category:
                        binding.viewPager.setCurrentItem(1);
                        changePageTitle(1);
                        binding.drawer.close();
                        break;

                    case R.id.nav_favorite:
                        binding.viewPager.setCurrentItem(2);
                        changePageTitle(2);
                        binding.drawer.close();
                        break;

                    case R.id.nav_settings:

                        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                        binding.drawer.close();

                        break;

                    case R.id.nav_rate:
                        Intent intent_rate = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName()));
                        startActivity(intent_rate);
                        break;
                }

                return false;
            }
        });


    }

    private void callingFragments() {
        List<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new CategoryFragment());
        fragmentArrayList.add(new FavoriteFragment());
        HomeAdapter adapter = new HomeAdapter(this,fragmentArrayList);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                binding.bottomMenu.getMenu().getItem(position).setChecked(true);
                changePageTitle(position);
            }});

        binding.bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home_item:

                        //getSupportFragmentManager().beginTransaction().replace(binding.container.getId(),new HomeFragment()).commit();
                        binding.viewPager.setCurrentItem(0);
                        binding.bottomMenu.getMenu().getItem(0).setChecked(true);
                        changePageTitle(0);
                        break;

                    case R.id.category_item:

                        //getSupportFragmentManager().beginTransaction().replace(binding.container.getId(),new CategoryFragment()).commit();
                        binding.viewPager.setCurrentItem(1);
                        binding.bottomMenu.getMenu().getItem(1).setChecked(true);
                        changePageTitle(1);
                        break;

                    case R.id.fav_item:

                        //getSupportFragmentManager().beginTransaction().replace(binding.container.getId(),new FavoriteFragment()).commit();
                        binding.viewPager.setCurrentItem(2);
                        binding.bottomMenu.getMenu().getItem(2).setChecked(true);
                        changePageTitle(2);
                        break;
                }

                return false;
            }
        });
    }


    //عوض کردن نام صفحه بر اساس اندیسی که در متد دریافت میشود
    private void changePageTitle(int index){
        switch (index){
            case 0:
                binding.txtPageTitle.setText(R.string.home);
                break;

            case 1:
                binding.txtPageTitle.setText(R.string.category);
                break;

            case 2:
                binding.txtPageTitle.setText(R.string.favorite);
                break;
        }
    }


    private void startBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu,menu);

        MenuItem search = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint(getString(R.string.search_Videos));
        searchView.setOnQueryTextListener(this);



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        //فرستادن کوئری سرچ به اکتیویتی سرچ
        intent.putExtra("search", query);
        startActivity(intent);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}