package com.example.cteam.Dto;

public class BoardDTO {
    private int num;
    private String id;
    private String subject;
    private String title;
    private String contents;
    private String file;
    private int re_ref;
    private int re_lev;
    private int re_seq;
    private String date;
    private  String comment;

    public BoardDTO () {}

    public BoardDTO(int num, String id, String subject, String title, String contents, String file, int re_ref, int re_lev, int re_seq, String date, String comment) {
        this.num = num;
        this.id = id;
        this.subject = subject;
        this.title = title;
        this.contents = contents;
        this.file = file;
        this.re_ref = re_ref;
        this.re_lev = re_lev;
        this.re_seq = re_seq;
        this.date = date;
        this.comment = comment;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getRe_ref() {
        return re_ref;
    }

    public void setRe_ref(int re_ref) {
        this.re_ref = re_ref;
    }

    public int getRe_lev() {
        return re_lev;
    }

    public void setRe_lev(int re_lev) {
        this.re_lev = re_lev;
    }

    public int getRe_seq() {
        return re_seq;
    }

    public void setRe_seq(int re_seq) {
        this.re_seq = re_seq;
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
