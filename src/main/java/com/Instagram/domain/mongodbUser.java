package com.Instagram.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class mongodbUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idSql;
    private Integer videoId;
    private String ime;
    private String prezime;
    private String username;
    private String datum;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public mongodbUser(Integer idSql,Integer videoId, String ime, String prezime, String username, String datum){
        this.idSql = idSql;
        this.videoId = videoId;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.datum = datum;
    }
    public mongodbUser(){}
}
