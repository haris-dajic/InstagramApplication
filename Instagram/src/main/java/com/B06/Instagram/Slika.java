package com.B06.Instagram;

import com.B06.Instagram.Repositories.SlikaRepository;
import oracle.sql.BLOB;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Entitet za tabelu slika
 */
@Entity
@Table(name = "Slika")
public class Slika {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slika_sequence")
    @SequenceGenerator(name = "slika_sequence", sequenceName = "SLIKA_SEQ")
    private Integer slikaID;

    @Column(name = "Slika")
    private Blob slika;

    @Column(name = "KorisnikID")
    private Integer korisnikID;

    public Slika(Blob b, Integer idKorisnika){
        slika = b;
        korisnikID = idKorisnika;
    }

    private SlikaRepository slikaRepository;
    public SlikaRepository getSlikaRepository() {
        return slikaRepository;
    }

    public Integer getSlikaID() {
        return slikaID;
    }

    public Blob getSlika() {
        return slika;
    }

    public Integer getKorisnikID() {
        return korisnikID;
    }

    public void dodajSliku(Slika s){
        slikaRepository.save(s);
    }

    public void promijeniSliku(Blob s){
        slika = s;
        //slikaRepository.delete(slikaID);
        slikaRepository.save(this);
    }

}
