package com.example.cteam.Dto;

public class BoardDetailDTO {
    private String num;
    private String subject;
    private String title;
    private String content;
    private String member_id;
    private String petname;
    private String imagepath;
    private String city;
    private String region;
    private String petimagepath;

    public BoardDetailDTO(String num, String subject, String title, String content, String member_id, String petname, String imagepath, String city, String region, String petimagepath) {

    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPetimagepath() {
        return petimagepath;
    }

    public void setPetimagepath(String petimagepath) {
        this.petimagepath = petimagepath;
    }
}
