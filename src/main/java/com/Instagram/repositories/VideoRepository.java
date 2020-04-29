package com.Instagram.repositories;

import com.Instagram.domain.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Integer> {
}
