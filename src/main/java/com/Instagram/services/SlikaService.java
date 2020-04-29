package com.Instagram.services;

import com.Instagram.domain.Slika;


public interface SlikaService {

     void dodajSliku(Slika s);
     void izbrisiSliku(Slika s);
     Slika findByUserId(Integer id);
     Slika findById(Integer id);
}
