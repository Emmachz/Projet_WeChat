package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Region {

    @Id
    @Column(name = "idRegion", nullable = false)
    private String idRegion;

    @Column(name = "region", nullable = false)
    private String region;

    public Region(String idRegion, String region){
        this.idRegion=idRegion;
        this.region=region;
    }
    public Region() {
    }

    public String getIdRegion() {
        return idRegion;
    }

    public void setDescription(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public void setIdRegion(String idRegion) {
        this.idRegion=idRegion;
    }
}
