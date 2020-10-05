package com.example.cteam.Dto;

import java.io.Serializable;

public class BoardDTO implements Serializable {
    private String id;
    private String subject;
    private String title;
    private String date;
    private String comment;

    public BoardDTO() {    }

    public BoardDTO(String id, String subject, String title, String date, String comment) {
        this.id = id;
        this.subject = subject;
        this.title = title;
        this.date = date;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
