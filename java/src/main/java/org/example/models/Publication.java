package org.example.models;

import java.sql.Date;

public class Publication {
    private int id,userid,like;
    private String contenu,username;
    private Date datepub;

    public Publication() {

    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", userid=" + userid +
                ", like=" + like +
                ", contenu='" + contenu + '\'' +
                ", username='" + username + '\'' +
                ", datepub=" + datepub +
                '}';
    }

    public Publication(int userid, int like, String contenu, String username, Date datepub) {
        this.userid = userid;
        this.like = like;
        this.contenu = contenu;
        this.username = username;
        this.datepub = datepub;
    }

    public Publication(int id, int userid, int like, String contenu, String username, Date datepub) {
        this.id = id;
        this.userid = userid;
        this.like = like;
        this.contenu = contenu;
        this.username = username;
        this.datepub = datepub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDatepub() {
        return datepub;
    }

    public void setDatepub(Date datepub) {
        this.datepub = datepub;
    }
}
