package com.example.newnavitrip.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TripPlan implements Serializable {
    private String trip_plan_id;
    private String trip_id;
    private String date;
    private String state;
    private String city;
    private String summery;
    private String description;
    private String guide;
    private String image;
    private String created_at;
    private String created_id;
    private ArrayList<TripTravelData> trip_travel;
    private ArrayList<TripStayData> stays_data;

    public String getTrip_plan_id() {
        return trip_plan_id;
    }

    public void setTrip_plan_id(String trip_plan_id) {
        this.trip_plan_id = trip_plan_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_id() {
        return created_id;
    }

    public void setCreated_id(String created_id) {
        this.created_id = created_id;
    }

    public ArrayList<TripTravelData> getTrip_travel() {
        return trip_travel;
    }

    public void setTrip_travel(ArrayList<TripTravelData> trip_travel) {
        this.trip_travel = trip_travel;
    }

    public ArrayList<TripStayData> getStays_data() {
        return stays_data;
    }

    public void setStays_data(ArrayList<TripStayData> stays_data) {
        this.stays_data = stays_data;
    }
}
