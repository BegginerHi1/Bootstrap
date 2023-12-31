package com.example.security.repositories;

import com.example.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u left join fetch u.roles where u.username=:username")
    User findByUsername(String username);
}
