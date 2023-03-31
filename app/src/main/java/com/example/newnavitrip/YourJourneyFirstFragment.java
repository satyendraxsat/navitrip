package com.example.newnavitrip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourJourneyFirstFragment#} factory method to
 * create an instance of this fragment.
 */
public class YourJourneyFirstFragment extends Fragment {
    public YourJourneyFirstFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_journey_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bb=getArguments();
        if(bb!=null){
        TextView textView=view.findViewById(R.id.tv_des);
        textView.setText(bb.getString("trip_description"));
         }
    }
}