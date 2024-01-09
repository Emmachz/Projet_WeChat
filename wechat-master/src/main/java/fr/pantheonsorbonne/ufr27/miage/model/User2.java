package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class UserEmma {

    @OneToMany
    @JoinColumn(name = "idUser")
    private int idUser;

    @ManyToOne
    @JoinColumn(name = "idMessage")
    private Message message;

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
