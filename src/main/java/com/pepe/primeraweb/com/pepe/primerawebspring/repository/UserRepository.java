package com.pepe.primeraweb.com.pepe.primerawebspring.repository;

import com.pepe.primeraweb.com.pepe.primerawebspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u From User u Where u.username = :username")
    User getUserByUsername(@Param("username") String username);

}
