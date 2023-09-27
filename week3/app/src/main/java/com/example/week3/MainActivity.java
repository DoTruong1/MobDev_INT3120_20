package com.example.week3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import com.example.week3.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
//        tabLayout=findViewById(R.id.tab_layout);
//        viewPager=findViewById(R.id.view_pager);

//        PagerAdapter pagerAdapter = new PagerAdapter(this);
//        pagerAdapter.addFragment(new FirstLinear(), "Clock");
//        pagerAdapter.addFragment(new SecondFragment(), "Date and time");
//        viewPager.setAdapter(pagerAdapter);
//        // Liên kết ViewPager2 với TabLayout
//        new TabLayoutMediator(tabLayout, viewPager,
//                (tab, position) -> tab.setText(pagerAdapter.getPageTitle(position))
//        ).attach();
//        tabLayout.setupWithViewPager(viewPager);
        // Add title in array list

//        tabLayout.setupWithViewPager(viewPager);
        // Khởi tạo adapter cho ViewPager




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        final int item1 = R.id.item1;
//        textView = findViewById(R.id.textView);
        if (id == R.id.first_linear) {
            openFragment(new FirstLinear());
            return true;
        } else if (id == R.id.first_table) {
            openFragment(new FirstTable());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void openFirstLinear() {
//        Fragment fragment = new FirstLinear();
//
//    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();
    }

    }