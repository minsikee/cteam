package com.example.cteam.Dto;

public class PetPhotoDTO {
    private String image_path;
    private String content;
    private String date;
    private String petName;

    public PetPhotoDTO(String image_path, String content, String date, String petName) {
        this.image_path = image_path;
        this.content = content;
        this.date = date;
        this.petName=petName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
