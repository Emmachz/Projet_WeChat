package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
@Table(name = "Versement")
@Entity
public class Versement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "versement_id", nullable = false)
    private Long versementId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userE_id", nullable = false)
    private User emetteurId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userR_id", nullable = false)
    private User receveurId;

    @Column(name = "versement_amount", nullable = false)
    private double versementAmount;

    public Versement(User emetteur, User receveur, double value){
        this.emetteurId = emetteur;
        this.receveurId = receveur;
        this.versementAmount = value;
    }

    public Versement() {

    }

    public void setVersementId(Long versementId) {
        this.versementId = versementId;
    }
    public Long getVersementId() {
        return versementId;
    }
    public void setEmetteurId(User emetteurId){
        this.emetteurId = emetteurId;
    }
    public User getEmetteurId(){
        return emetteurId;
    }

    public void setReceveurId(User receveurId){
        this.receveurId = receveurId;
    }
    public User getReceveurId(){
        return receveurId;
    }
    public void setVersementAmount(double versementAmount){
        this.versementAmount = versementAmount;
    }
    public double getVersementAmount(){
        return versementAmount;
    }
}
