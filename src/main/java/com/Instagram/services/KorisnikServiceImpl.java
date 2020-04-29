package com.Instagram.services;

import com.Instagram.repositories.KorisnikRepository;
import com.Instagram.domain.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    private KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikServiceImpl(KorisnikRepository korisnikRepository){
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public ArrayList<Korisnik> uzmiSveKorisnike() {

        //provjeriti ovu konverziju
        ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
        listaKorisnika = (ArrayList<Korisnik>)korisnikRepository.findAll();
        return listaKorisnika;
    }

    @Override
    public void dodajKorisnika(Korisnik korisnik) {
        korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik dajKorisnika(Korisnik korisnik) {
        return korisnikRepository.findOne(korisnik.getKorisnikID());
    }

    @Override
    public Korisnik findById(Integer id) {
        return korisnikRepository.findOne(id);
    }

    @Override
    public Korisnik login(String username, String password) {

        ArrayList<Korisnik> lista = (ArrayList<Korisnik>)korisnikRepository.findAll();

        for (Korisnik k : lista) {

            if(k.getUsername().equals(username) && k.getPassword().equals(password))
                return k;
        }

        return  null;
    }

    @Override
    public Korisnik findByUsername(String username) {

        ArrayList<Korisnik> lista = (ArrayList<Korisnik>)korisnikRepository.findAll();

        for (Korisnik k : lista) {

            if(k.getUsername().equals(username))
                return k;
        }

        return  null;

    }
}
