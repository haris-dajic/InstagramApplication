package com.B06.Instagram.Repositories;

import com.B06.Instagram.Korisnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends CrudRepository<Korisnik, Integer>{

}
