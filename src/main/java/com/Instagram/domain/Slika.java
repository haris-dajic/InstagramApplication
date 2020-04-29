package com.Instagram.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class Slika {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer slikaId;

    private Blob slika;

    private Integer korisnikId;

    public Slika(Blob slika, Integer korisnikID) {
        this.slika = slika;
        this.korisnikId = korisnikID;
    }

    public Slika() {
    }

    public Integer getSlikaID() {
        return slikaId;
    }

    public Blob getSlika() {
        return slika;
    }

    public Integer getKorisnikID() {
        return korisnikId;
    }

    public void setSlikaID(Integer slikaID) {
        this.slikaId = slikaID;
    }

    public void setSlika(Blob slika) {
        this.slika = slika;
    }

    public void setKorisnikID(Integer korisnikID) {
        this.korisnikId = korisnikID;
    }
}
