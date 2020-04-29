package com.Instagram.services;

import com.Instagram.domain.Video;

import java.util.ArrayList;

public interface VideoService {

     void dodajVideo(Video video);
     void izbrisiVideo(Video video);
     ArrayList<Video> getAllVideos();
     Video find(Video v);
     Video findById(int id);
     ArrayList<Video> findByUserId(int id);

}
