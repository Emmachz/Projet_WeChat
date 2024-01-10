package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRegion", nullable = false)
    private int idRegion;

    @Column(name = "region", nullable = false)
    private String region;


}
