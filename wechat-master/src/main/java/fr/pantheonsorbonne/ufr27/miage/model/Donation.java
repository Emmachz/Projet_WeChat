package fr.pantheonsorbonne.ufr27.miage.model;

import fr.pantheonsorbonne.ufr27.miage.dto.Require;
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


    @OneToMany
    @JoinColumn(name = "requires")
    private Collection<Require>  requires;


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

    public Collection<Require> getRequires() {
        return requires;
    }

    public void setRequires(Collection<Require> requires) {
        this.requires = requires;
    }

}
