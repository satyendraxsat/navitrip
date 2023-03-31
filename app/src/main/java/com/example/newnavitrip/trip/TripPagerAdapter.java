package com.example.newnavitrip.trip;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class TripPagerAdapter extends FragmentStateAdapter{
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public TripPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    public Fragment getFragment(int pos){
        return fragmentList.get(pos);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
