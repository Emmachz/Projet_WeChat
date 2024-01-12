package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlert", nullable = false)
    private Integer id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idRegion", nullable = true)
    private Region idRegion;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "region", nullable = false)
    private String region;

    public Alert(Integer id, String description, String region) {
        this.id = id;
        this.description = description;
        this.region = region;
    }

    public Alert() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}