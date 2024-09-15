package com.digitalrazgrad.RestApiWithSwagger.repository;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
