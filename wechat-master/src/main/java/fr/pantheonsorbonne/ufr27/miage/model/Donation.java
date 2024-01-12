package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.Collection;

@Table(name = "Donation")
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donationId", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 45)
    private String description;



    @Column(name = "helps", nullable = false, length = 45)
    @OneToMany
    private Collection<Help> helps;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public Collection<Help> getHelps() {
        return helps;
    }

    public void setHelps(Collection<Help> helps) {
        this.helps = helps;
    }

}
