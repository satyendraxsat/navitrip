package com.example.newnavitrip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newnavitrip.adapter.TourPlanAdapter;
import com.example.newnavitrip.model.TourPlan;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourJourneySecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourJourneySecondFragment extends Fragment {


    public YourJourneySecondFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_journey_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bb=getArguments();
        if(bb!=null){
//            TextView textView=view.findViewById(R.id.tv_des);
//            textView.setText(bb.getString("tour_plan"));
            String data=bb.getString("tour_plan");
            String[] datalist=data.split("\n\n");
            ArrayList<TourPlan> tourPlanArrayList=new ArrayList<>();
            int len=datalist.length;
            for(int i=0;i<len-1;i++){
                String[] item=datalist[i].split("<b>");
                TourPlan tourPlan=new TourPlan(item[0],item[1]);
                tourPlanArrayList.add(tourPlan);
            }

            RecyclerView rv=view.findViewById(R.id.rv_tour_plan);
            TourPlanAdapter adapter=new TourPlanAdapter(tourPlanArrayList);
            rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            rv.setAdapter(adapter);
        }
    }
}