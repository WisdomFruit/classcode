package com.jumpdigital.nico.classcode.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jumpdigital.nico.classcode.R;
import com.jumpdigital.nico.classcode.adapter.ViewPagerAdapter;
import com.jumpdigital.nico.classcode.fragment.DashboardFragment;
import com.jumpdigital.nico.classcode.fragment.RecordFragment;
import com.jumpdigital.nico.classcode.fragment.ScannerFragment;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add fragment here nigga
        viewPagerAdapter.addFragment(new RecordFragment(), "Record");
        viewPagerAdapter.addFragment(new DashboardFragment(), "Dashboard");
        viewPagerAdapter.addFragment(new ScannerFragment(), "Scanner");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // set icons of fragments here nigga
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_record);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_scanner);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }
}
