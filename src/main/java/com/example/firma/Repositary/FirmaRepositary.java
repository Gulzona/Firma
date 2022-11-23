package com.example.firma.Repositary;

import com.example.firma.Entity.Firma;
import com.example.firma.Entity.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FirmaRepositary extends JpaRepository<Firma,Integer> {

 boolean existsByFirmaNomi(String firmaNomi);

}

