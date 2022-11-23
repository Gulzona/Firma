package com.example.firma.Repositary;

import com.example.firma.Entity.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IshchiRepositary extends JpaRepository<Ishchi,Integer> {

    boolean existsByEmail(String email);
    Optional<Ishchi> findById(Integer id);
}
