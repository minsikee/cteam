package com.example.cteam.Dto;

import java.io.Serializable;

/**
 * Created by LG on 2017-02-08.
 */

public class MyItem implements Serializable {
    public String name;
    public int age;
    public int weight;
    public String gender;
    public int image_path;

    public MyItem(String name, int age, int weight, String gender, int image_path) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.image_path = image_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getImage_path() {
        return image_path;
    }

    public void setImage_path(int image_path) {
        this.image_path = image_path;
    }
}
