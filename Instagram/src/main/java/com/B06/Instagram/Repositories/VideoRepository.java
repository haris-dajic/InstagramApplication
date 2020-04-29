package com.B06.Instagram.Repositories;

import com.B06.Instagram.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<Video, Integer> {
}
