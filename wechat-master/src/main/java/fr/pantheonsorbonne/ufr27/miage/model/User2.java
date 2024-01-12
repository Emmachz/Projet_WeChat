package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class User2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idRegion")
    private Region idRegion;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "fristName", nullable = false)
    private String fristName;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "vetementKg", nullable = false)
    private int vetementKg;

}