package com.example.firma.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ishchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private String familya;
    @Column(nullable = false)
    private String telRaqam;
    @Column(nullable = false,unique = true)
    private String email;

    @OneToOne
    private Manzil manzil;

    @ManyToOne
    private Bolim bolim;



}
