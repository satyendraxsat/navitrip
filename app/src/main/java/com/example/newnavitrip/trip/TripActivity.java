package com.example.newnavitrip.trip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newnavitrip.R;
import com.example.newnavitrip.databinding.ActivityTripBinding;
import com.example.newnavitrip.model.TripPlan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TripActivity extends AppCompatActivity {
private ActivityTripBinding binding;
    ViewPager2 myViewPager2;
    TripPagerAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_trip);

        binding.tabTrip.addTab(binding.tabTrip.newTab().setText("Detail"));
        binding.tabTrip.addTab(binding.tabTrip.newTab().setText("Stay"));
        binding.tabTrip.addTab(binding.tabTrip.newTab().setText("Guide"));
        binding.tabTrip.addTab(binding.tabTrip.newTab().setText("Travel"));
        binding.tabTrip.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabTrip.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TripPagerFragment tripPagerFragment= (TripPagerFragment) myAdapter.getFragment(binding.viewpagerTrip.getCurrentItem());
                CharSequence text = tab.getText();
                if ("Detail".equals(text)) {
                    tripPagerFragment.setDetailVisible();
                } else if ("Stay".equals(text)) {
                    tripPagerFragment.setStayVisible();
                } else if ("Guide".equals(text)) {
                    tripPagerFragment.setGuideVisible();
                } else if ("Travel".equals(text)) {
                    tripPagerFragment.setTravelVisible();
                }else {
                    tripPagerFragment.setDetailVisible();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        try {
            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String s1 = sh.getString("trip_plan_data", "");

            Type listType = new TypeToken<ArrayList<TripPlan>>() {
            }.getType();
            ArrayList<TripPlan> tripPlans =  new Gson().fromJson(s1, listType);

            int triplength=tripPlans.size();

            myAdapter = new TripPagerAdapter(this);

            for (int i=0;i<triplength;i++){
                Bundle bundle=new Bundle();
                bundle.putSerializable("tripplan",tripPlans.get(i));
                TripPagerFragment fragment=new TripPagerFragment();
                fragment.setArguments(bundle);
                myAdapter.addFragment(fragment);
            }

            binding.viewpagerTrip.setAdapter(myAdapter);
            binding.viewpagerTrip.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    binding.tabTrip.selectTab(binding.tabTrip.getTabAt(0));
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.person:
                    Intent i1 = new Intent(TripActivity.this,com.example.newnavitrip.curency_conversion.class);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    startActivity(i1);
                    return true;
                case R.id.home:
                    finish();
                    return true;
                case R.id.chatbox:
                    Intent i = new Intent(TripActivity.this,com.example.newnavitrip.chatbox.class);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    startActivity(i);
                    return true;
            }
            return false;
        });
    }

}
