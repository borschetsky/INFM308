package com.example.infm308.models;

import java.io.Serializable;

public class Person implements Serializable {
    public String name, height, mass, gender, birth_year, avatar, homeworld = null;
    public Person (){

    }

    public void setAvatar (String url) {
        this.avatar = url;
    }
}
