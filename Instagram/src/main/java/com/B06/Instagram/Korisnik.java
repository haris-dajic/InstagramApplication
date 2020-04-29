package com.B06.Instagram;

import javax.persistence.*;

/**
 * Klasa namijenjana za rad sa tabelom korisnik.
 */
@Entity
@Table(name = "Korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_sequence")
    @SequenceGenerator(name = "korisnik_sequence", sequenceName = "KORIS_SEQ")
    private Integer korisnikID;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "Email")
    private String email;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    /*@Autowired
    private KorisnikRepository korisnikRepository;
    public KorisnikRepository getUserRepository() {
        return korisnikRepository;
    }
*/
    public Korisnik(String name, String surname, String mail, String user, String pw){
        ime = name;
        prezime = surname;
        email = mail;
        username = user;
        password = pw;
    }

    public Integer getKorisnikID() {
        return korisnikID;
    }

    /*public void dodajKorisnika(Korisnik k){
        korisnikRepository.save(k);
    }

    public void izbrisiKorisnika(Integer id){
        korisnikRepository.delete(id);
    }*/
}
