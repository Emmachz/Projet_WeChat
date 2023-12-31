package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Government {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fname", nullable = false, length = 45)
    private String fname;

    @Column(name = "lname", nullable = false, length = 45)
    private String lname;

    @Column(name = "email", nullable = false)
    private String email;
}
