package com.Instagram.repositories;

import com.Instagram.domain.mongodbUser;
import org.springframework.data.repository.CrudRepository;

public interface mongodbRepository extends CrudRepository<mongodbUser, Integer> {
}
