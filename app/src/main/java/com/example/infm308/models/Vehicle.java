package com.example.infm308.models;

import java.io.Serializable;

public class Vehicle implements Serializable {
    public  String name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers, cargo_capacity, vehicle_class, avatar;

    public Vehicle() {

    }

    public void setAvatar (String url) {
        this.avatar = url;
    }
}
