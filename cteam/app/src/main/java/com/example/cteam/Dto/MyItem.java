package com.example.cteam.Dto;

import java.io.Serializable;

/**
 * Created by LG on 2017-02-08.
 */

public class MyItem implements Serializable {
    public String name;
    public int age;
    public int weidth;
    public String gender;
    public String image_path;

    public MyItem(String name, int age, int weidth, String gender, String image_path) {
        this.name = name;
        this.age = age;
        this.weidth = weidth;
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

    public int getWeidth() {
        return weidth;
    }

    public void setWeidth(int weidth) {
        this.weidth = weidth;
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
