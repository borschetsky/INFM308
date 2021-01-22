package com.example.infm308.models;

public class Person {
    public String name, height, mass, gender, birth_year, avatar = null;
    public Person (){

    }

    public void setAvatar (String url) {
        this.avatar = url;
    }
}
