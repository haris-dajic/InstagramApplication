package com.B06.Instagram.Repositories;

import com.B06.Instagram.Slika;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlikaRepository extends CrudRepository<Slika, Integer> {

}
