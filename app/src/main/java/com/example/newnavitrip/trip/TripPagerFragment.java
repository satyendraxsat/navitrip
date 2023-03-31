package com.example.newnavitrip.trip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newnavitrip.R;
import com.example.newnavitrip.URLs;
import com.example.newnavitrip.databinding.FragmentTripPagerItemBinding;
import com.example.newnavitrip.model.TripPlan;
import com.example.newnavitrip.model.TripStayData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TripPagerFragment extends Fragment {
    private FragmentTripPagerItemBinding binding;
    private TripPlan tripPlan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trip_pager_item, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tripPlan = (TripPlan) bundle.getSerializable("tripplan");
            if (tripPlan != null) {
                setDetailVisible();
                SimpleDateFormat inSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat outSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                Date date = null;
                String showdate = "";
                try {
                    date = inSimpleDateFormat.parse(tripPlan.getDate().split(" ")[0]);
                    assert date != null;
                    showdate = outSimpleDateFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                binding.tvDate.setText(showdate);
                binding.tvDetailDescription.setText(tripPlan.getDescription());
                ArrayList<TripStayData> tripStayData = tripPlan.getStays_data();
                if (tripStayData != null && tripStayData.size() != 0) {
                    binding.tvHotelDescription.setText(tripStayData.get(0).getDescription());
                    binding.tvHotelName.setText(tripStayData.get(0).getHotel_name());
                    binding.tvHotelRoom.setText(tripStayData.get(0).getRoom_type());
                    binding.tvHotelMeal.setText(tripStayData.get(0).getMeal_type());
                    String hotelImgPath = URLs.ROOT_URL + tripPlan.getImage();
                    Glide.with(this).load(hotelImgPath).into(binding.imgHotel);
                }

            }
        }

    }

    public void setDetailVisible() {
        binding.layoutDetail.setVisibility(View.VISIBLE);
        binding.layoutStay.setVisibility(View.GONE);
        binding.layoutGuide.setVisibility(View.GONE);
        binding.layoutTravel.setVisibility(View.GONE);
        binding.layoutNoData.setVisibility(View.GONE);
    }

    public void setStayVisible() {
        if (tripPlan != null && tripPlan.getStays_data() != null && tripPlan.getStays_data().size() != 0) {
            binding.layoutDetail.setVisibility(View.GONE);
            binding.layoutStay.setVisibility(View.VISIBLE);
            binding.layoutGuide.setVisibility(View.GONE);
            binding.layoutTravel.setVisibility(View.GONE);
            binding.layoutNoData.setVisibility(View.GONE);
        } else setNoDataVisible();
    }

    public void setGuideVisible() {
        if (tripPlan != null && tripPlan.getGuide() != null && !tripPlan.getGuide().equals("NA")) {
            binding.layoutDetail.setVisibility(View.GONE);
            binding.layoutStay.setVisibility(View.GONE);
            binding.layoutGuide.setVisibility(View.VISIBLE);
            binding.layoutTravel.setVisibility(View.GONE);
            binding.layoutNoData.setVisibility(View.GONE);
        } else setNoDataVisible();
    }

    public void setTravelVisible() {
        if (tripPlan != null && tripPlan.getTrip_travel() != null && tripPlan.getTrip_travel().size() != 0) {
            binding.layoutDetail.setVisibility(View.GONE);
            binding.layoutDetail.setVisibility(View.GONE);
            binding.layoutStay.setVisibility(View.GONE);
            binding.layoutGuide.setVisibility(View.GONE);
            binding.layoutTravel.setVisibility(View.VISIBLE);
            binding.layoutNoData.setVisibility(View.GONE);
        } else setNoDataVisible();
    }

    public void setNoDataVisible() {
        binding.layoutDetail.setVisibility(View.GONE);
        binding.layoutStay.setVisibility(View.GONE);
        binding.layoutGuide.setVisibility(View.GONE);
        binding.layoutTravel.setVisibility(View.GONE);
        binding.layoutNoData.setVisibility(View.VISIBLE);
    }


}
