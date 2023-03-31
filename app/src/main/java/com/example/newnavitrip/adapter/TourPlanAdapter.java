package com.example.newnavitrip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newnavitrip.R;
import com.example.newnavitrip.model.TourPlan;

import java.util.ArrayList;

public class TourPlanAdapter extends RecyclerView.Adapter<TourPlanAdapter.MyViewHolder> {

    private ArrayList<TourPlan> tourPlanArrayList;

    public TourPlanAdapter(ArrayList<TourPlan> tourPlanArrayList) {
        this.tourPlanArrayList=tourPlanArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tour_plan_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TourPlan tourPlan= tourPlanArrayList.get(position);
        holder.getDateTextView().setText(tourPlan.getDate());
        holder.getDesTextView().setText(tourPlan.getDescription());
    }

    @Override
    public int getItemCount() {
        return tourPlanArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView date;
        private final TextView des;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             date=itemView.findViewById(R.id.tv_date);
             des=itemView.findViewById(R.id.tv_des);
        }

        public TextView getDateTextView() {
            return date;
        }
        public TextView getDesTextView() {
            return des;
        }
    }
}
