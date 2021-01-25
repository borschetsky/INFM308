package com.example.infm308.models;

import java.io.Serializable;

public class Person implements Serializable {
    public int id;
    public String name, height, mass, gender, birth_year, avatar, homeworld = null;
    public Person(int id, String gender, String name, String birthYear, String avatar){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth_year = birthYear;
        this.avatar = avatar;
    }
    public Person(){

    }
    public void setAvatar (String url) {
        this.avatar = url;
    }
}
