package com.B06.Instagram;

import com.B06.Instagram.Repositories.VideoRepository;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

/**
 * Klasa Video za metode dodavanja ili brisanja videa iz baze podataka
 */
@Entity
@Table(name = "Video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_sequence")
    @SequenceGenerator(name = "video_sequence", sequenceName = "VIDEO_SEQ")
    private Integer videoID;

    @Column(name = "Video")
    private Blob video;

    @Column(name = "KorisnikID")
    private Integer korisnikID;

    private VideoRepository videoRepository;

    public VideoRepository getVideoRepository() {
        return videoRepository;
    }

    public Video(Blob b, Integer id){
        video = b;
        korisnikID = id;
    }

    public Integer getVideoID() {
        return videoID;
    }

    public Blob getVideo() {
        return video;
    }

    public Integer getKorisnikID() {
        return korisnikID;
    }

    public void dodajVideo(Video v){
        videoRepository.save(v);
    }

    public void izbrisiVideo(){
        //videoRepository.delete(videoID);
    }

    public static List<Blob> getAllById(int korisnikId) {

        Iterable<Video> all_videos =  videoRepository.findAll();
        List<Blob> videos = null;

        for (Video video_itt: all_videos) {
            if(video_itt.getKorisnikID() == korisnikId)
                videos.add(video_itt.getVideo());
        }

        return videos;
    }
}
