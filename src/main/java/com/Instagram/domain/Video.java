package com.Instagram.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class Video {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer videoId;

    private Blob video;

    private Integer korisnikId;

    public Video(Blob video, Integer korisnikID) {
        this.video = video;
        this.korisnikId = korisnikID;
    }

    public Video() {
    }

    public Integer getVideoID() {
        return videoId;
    }

    public Blob getVideo() {
        return video;
    }

    public Integer getKorisnikID() {
        return korisnikId;
    }

    public void setVideoID(Integer videoID) {
        this.videoId = videoID;
    }

    public void setVideo(Blob video) {
        this.video = video;
    }

    public void setKorisnikID(Integer korisnikID) {
        this.korisnikId = korisnikID;
    }
}
