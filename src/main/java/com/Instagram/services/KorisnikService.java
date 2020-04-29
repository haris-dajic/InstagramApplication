package com.Instagram.services;

import com.Instagram.domain.Korisnik;

import java.util.ArrayList;

public interface KorisnikService {

     ArrayList<Korisnik> uzmiSveKorisnike();

     void dodajKorisnika(Korisnik korisnik);

     Korisnik dajKorisnika(Korisnik korisnik);

     Korisnik findById(Integer id);

     Korisnik login(String username, String password);

     Korisnik findByUsername(String username);


}
