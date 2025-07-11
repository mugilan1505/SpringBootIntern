package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {
    Optional<RegisterDetails> findByUserName(String userName);

    @Query("SELECT u FROM RegisterDetails u JOIN u.roles r WHERE r.name = :roleName")
    List<RegisterDetails> findByRoleName(@Param("roleName") String roleName);
}
