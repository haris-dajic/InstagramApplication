package com.Instagram.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer korisnikId;

    private String ime;

    private String prezime;

    private String email;

    private String username;

    private String password;

    public Korisnik(Integer id, String ime, String prezime, String email, String username, String password) {
        this.korisnikId = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Korisnik() {}

    public Integer getKorisnikID() {
        return korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setKorisnikID(Integer korisnikID) {
        this.korisnikId = korisnikID;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
