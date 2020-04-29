package com.Instagram.services;

import com.Instagram.domain.Korisnik;
import com.Instagram.domain.Video;
import com.Instagram.repositories.KorisnikRepository;
import com.Instagram.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class VideoServiveImpl implements VideoService {

    private VideoRepository videoRepository;
    private KorisnikRepository korisnikRepository;

    @Autowired
    public VideoServiveImpl(VideoRepository videoRepository){
        this.videoRepository = videoRepository;
    }

    @Override
    public void dodajVideo(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void izbrisiVideo(Video video) {
        videoRepository.delete(video);
    }

    @Override
    public ArrayList<Video> getAllVideos() {
        return (ArrayList<Video>) videoRepository.findAll();
    }

    @Override
    public Video find(Video v) {
        return videoRepository.findOne(v.getVideoID());
    }

    @Override
    public Video findById(int id) {
        return videoRepository.findOne(id);
    }

    @Override
    public ArrayList<Video> findByUserId(int id) {
        ArrayList<Video> videos = (ArrayList<Video>) videoRepository.findAll();
        ArrayList<Video> allUsersVideos = new ArrayList<Video>();

        for (Video v : videos) {
            if(v.getKorisnikID() == id)
                allUsersVideos.add(v);
        }

        return allUsersVideos;
    }

}
