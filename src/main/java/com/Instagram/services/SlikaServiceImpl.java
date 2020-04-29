package com.Instagram.services;

import com.Instagram.domain.Slika;
import com.Instagram.repositories.SlikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SlikaServiceImpl implements SlikaService {

    private SlikaRepository slikaRepository;


    @Autowired
    public SlikaServiceImpl(SlikaRepository slikaRepository){
        this.slikaRepository = slikaRepository;
    }

    @Override
    public void dodajSliku(Slika s) {
        slikaRepository.save(s);

    }

    @Override
    public void izbrisiSliku(Slika s) {
        slikaRepository.delete(s);
    }

    @Override
    public Slika findByUserId(Integer id) {
        ArrayList<Slika> slike = (ArrayList<Slika>) slikaRepository.findAll();
        Slika s = new Slika();

        for(Slika sl : slike){
            if(sl.getKorisnikID() == id)
                s = sl;
        }
        return s;
    }

    @Override
    public Slika findById(Integer id) {
        return slikaRepository.findOne(id);
    }

}
