package com.example.newnavitrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.newnavitrip.adapter.ViewPager2Adapter;
import com.example.newnavitrip.model.TripData;
import com.example.newnavitrip.model.TripPlan;
import com.example.newnavitrip.trip.TripActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class YourJourneyActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy, NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    //global variables
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourjourney);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(view -> drawer.openDrawer(Gravity.LEFT));
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.person:
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        startActivity(new Intent(getApplicationContext(),curency_conversion.class));
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.chatbox:
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                        startActivity(new Intent(getApplicationContext(),chatbox.class));
                        return true;
                }
                return false;
            }
        });





//        binding = ActivityYourjourneyBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//
//        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

       /* ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

       viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);



        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/


        ImageView homemenu=findViewById(R.id.iv_home_menu);
        homemenu.setOnClickListener(view -> drawer.openDrawer(GravityCompat.START));
        TextView logout=findViewById(R.id.tv_logout);
        logout.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putBoolean("userlogin", false);
            myEdit.apply();
            Intent intent = new Intent(YourJourneyActivity.this, login.class);
            startActivity(intent);
            finishAffinity();
        });
        try {
            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String s1 = sh.getString("trip_data", "");
            Type listType = new TypeToken<TripData>() {
            }.getType();
            TripData tripData =  new Gson().fromJson(s1, listType);
            ImageView img=findViewById(R.id.imageView);
            String hotelImgPath= URLs.ROOT_URL+tripData.getPath();
            Glide.with(this).load(hotelImgPath).into(img);
            TextView trip=findViewById(R.id.tv_trip_name);
            trip.setText(tripData.getTrip_name());
            TextView exlpore=findViewById(R.id.tv_explore_more);
            exlpore.setOnClickListener(view -> startActivity(new Intent(YourJourneyActivity.this, TripActivity.class)));

            ArrayList<TripPlan> tripPlanlist=tripData.getTrip_plan();
//            ArrayList<String> tourplans=new ArrayList<>();
//            tripPlanlist.forEach(tripPlan -> {
//                tourplans.add(tripPlan.getSummery());
//            });
            String tourplans="";
            for(int i=0;i<tripPlanlist.size()-1;i++){
                String[] s =tripPlanlist.get(i).getSummery().split("\r\n");

                tourplans = tourplans + s[0]+"<b>"+s[1] + "\n\n";
            }
            viewPager2 = findViewById(R.id.viewPager2);
            tabLayout = findViewById(R.id.tabLayout);
            titles = new ArrayList<String>();
            titles.add("Info");
            titles.add("Tour Plan");
            titles.add("Map");
            setViewPagerAdapter(tripData.getDescription(),tourplans);
            new TabLayoutMediator(tabLayout, viewPager2, this).attach();

        }catch (Exception e){
            e.printStackTrace();
        }

//       setSupportActionBar(binding.appBarYourjourney.toolbar);
// hide the title bar
//        binding.appBarYourjourney.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_yourjourney);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.yourjourney, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_yourjourney);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    public void setViewPagerAdapter(String des,String tourplan) {
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        ArrayList<Fragment> fragmentList = new ArrayList<>(); //creates an ArrayList of Fragments
        Bundle bundle=new Bundle();
        bundle.putString("trip_description",des);
        YourJourneyFirstFragment fragment=new YourJourneyFirstFragment();
        fragment.setArguments(bundle);
        fragmentList.add(fragment);

        Bundle bundle2=new Bundle();
        bundle2.putString("tour_plan",tourplan);
        YourJourneySecondFragment fragment2=new YourJourneySecondFragment();
        fragment2.setArguments(bundle2);
        fragmentList.add(fragment2);
        fragmentList.add(new YourJourneyThirdFragment());
        viewPager2Adapter.setData(fragmentList); //sets the data for the adapter
        viewPager2.setAdapter(viewPager2Adapter);

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }








}