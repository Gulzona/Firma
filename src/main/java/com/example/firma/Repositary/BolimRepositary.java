package com.example.firma.Repositary;

import com.example.firma.Entity.Bolim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolimRepositary extends JpaRepository<Bolim,Integer> {
    boolean existsById(Integer id);
    boolean existsByBolimNomiAndFirmaId(String bolimNomi, Integer firma_id);
}
