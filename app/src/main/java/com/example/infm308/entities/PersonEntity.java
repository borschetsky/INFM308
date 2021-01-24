package com.example.infm308.entities;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Unique;

public class PersonEntity extends SugarRecord {

    @Unique
    @Column(name = "person_id")
    private Long personId;
    @Unique
    @Column(name = "person_name")
    private String name;
    @Column(name = "person_height")
    private String height;
    @Column(name = "person_mass")
    private String mass;
    @Column(name = "person_gender")
    private String gender;
    @Column(name = "person_birth_year")
    private String birth_year;
    @Column(name = "person_avatar")
    private String avatar;

    public PersonEntity() {
    }

    public PersonEntity(String name, String height, String mass, String gender, String birth_year, String avatar) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.gender = gender;
        this.birth_year = birth_year;
        this.avatar = avatar;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
