package com.example.newnavitrip.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TripData implements Serializable {
    private String trip_id;
    private String user_name;
    private String trip_name;
    private String start_date;
    private String end_date;
    private String description;
    private String tour_desctiption;
    private String path;
    private String tour_leader;
    private ArrayList<TripPlan> trip_plan;


    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTour_desctiption() {
        return tour_desctiption;
    }

    public void setTour_desctiption(String tour_desctiption) {
        this.tour_desctiption = tour_desctiption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTour_leader() {
        return tour_leader;
    }

    public void setTour_leader(String tour_leader) {
        this.tour_leader = tour_leader;
    }

    public ArrayList<TripPlan> getTrip_plan() {
        return trip_plan;
    }

    public void setTrip_plan(ArrayList<TripPlan> trip_plan) {
        this.trip_plan = trip_plan;
    }
}
