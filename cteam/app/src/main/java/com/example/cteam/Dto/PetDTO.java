package com.example.cteam.Dto;

import java.io.Serializable;

/**
 * Created by LG on 2017-02-08.
 */

public class PetDTO implements Serializable {
    public String name;
    public String age;
    public String weight;
    public String gender;
    public String image_path;

    public PetDTO(String name, String age, String weight, String gender, String image_path) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.image_path = image_path;
    }

    public PetDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
