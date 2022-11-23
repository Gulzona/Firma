package com.example.firma.Repositary;

import com.example.firma.Entity.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManzilRepositary extends JpaRepository<Manzil,Integer> {
   boolean existsByUyRaqami(Integer uyRaqami);
}
