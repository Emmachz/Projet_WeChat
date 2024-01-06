package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ExternalSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ES", nullable = false)
    private Long esId;

    @Column(name = "email_ES", nullable = false, unique = true)
    private String emailES;
}
