package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class FamilyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcard_id", nullable = false)
    private Long fcard_id;

    public void setFcard_id(Long fcardId) {
        this.fcard_id = fcardId;
    }

    public Long getFcard_id() {
        return fcard_id;
    }


}
