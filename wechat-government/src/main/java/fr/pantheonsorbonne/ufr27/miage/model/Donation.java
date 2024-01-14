package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.Collection;

@Table(name = "Donation")
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donationId", nullable = false)
    private Integer donationId;

    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @Column(name = "regionOfNeed", nullable = false, length = 45)
    private String regionOfNeed;

    @Column(name = "moneySupport")
    private double moneySupport;

    @Column(name = "timeSupport")
    private double timeSupport;

    @Column(name = "clotheSupport")
    private double clotheSupport;

    @Column(name = "moneyGived")
    private double moneyGived;

    @Column(name = "timeGived")
    private double timeGived;

    @Column(name = "clotheGived")
    private double clotheGived;

    public Donation(String description, String regionOfNeed, double moneySupport, double timeSupport, double clotheSupport, double moneyGived, double timeGived, double clotheGived) {
        this.description = description;
        this.regionOfNeed = regionOfNeed;
        this.moneySupport = moneySupport;
        this.timeSupport = timeSupport;
        this.clotheSupport = clotheSupport;
        this.moneyGived = moneyGived;
        this.timeGived = timeGived;
        this.clotheGived = clotheGived;

    }

    public Donation() {
    }



    public Integer getId() {
        return donationId;
    }


    public String getDescription(){
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public String getRegionOfNeed() {
        return regionOfNeed;
    }

    public void  setRegionOfNeed(String regionOfNeed) {this.regionOfNeed = regionOfNeed;}

    public double getMoneySupport() {
        return moneySupport;
    }

    public void  setMoneySupport(double moneySupport) {this.moneySupport = moneySupport;}


    public double getTimeSupport() {
        return timeSupport;
    }

    public void  setTimeSupport(double timeSupport) {this.timeSupport = timeSupport;}


    public double getClotheSupport() {
        return clotheSupport;
    }

    public void  setClotheSupport(double clotheSupport) {this.clotheSupport = clotheSupport;}

    public double getMoneyGived() {
        return moneyGived;
    }

    public void  setMoneyGived(double moneyGived) {this.moneyGived = moneyGived;}

    public double getTimeGived() {
        return timeGived;
    }

    public void  setTimeGived(double timeGived) {this.timeGived = timeGived;}

    public double getClotheGived() {
        return clotheGived;
    }

    public void  setClotheGived(double clotheGived) {this.clotheGived = clotheGived;}

}
