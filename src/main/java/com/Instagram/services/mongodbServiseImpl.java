package com.Instagram.services;

import com.Instagram.domain.mongodbUser;
import com.Instagram.repositories.mongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class mongodbServiseImpl implements mongodbService{

    private mongodbRepository mongoRepo;

    @Autowired
    public mongodbServiseImpl(mongodbRepository m){mongoRepo = m;}


    @Override
    public void spremiPodatke(mongodbUser u) {
        mongoRepo.save(u);
    }
}
