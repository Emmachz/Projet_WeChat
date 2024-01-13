package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "helpId", nullable = false)
    private int helpId;


    @Column(name = "region", nullable = false, length = 45)
    private String region;

    @Column(name = "moneySupport", nullable = false)
    private double moneySupport;

    @Column(name = "timeSupport", nullable = false)
    private double timeSupport;

    @Column(name = "clotheSupport", nullable = false)
    private double clotheSupport;



    public Help(int helpId, String region, double moneySupport, double timeSupport, double clotheSupport) {
        this.helpId  = helpId;
        this.region=region;
        this.moneySupport = moneySupport;
        this.timeSupport = timeSupport;
        this.clotheSupport = clotheSupport;
    }

    public Help() {
    }

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getMoneySupport() {
        return moneySupport;
    }

    public void setMoneySupport(double moneySupport) {
        this.moneySupport = moneySupport;
    }

    public double getTimeSupport() {
        return timeSupport;
    }

    public void setTimeSupport(double timeSupport) {
        this.timeSupport = timeSupport;
    }

    public double getClotheSupport() {
        return clotheSupport;
    }

    public void setClotheSupport(double clotheSupport) {
        this.clotheSupport = clotheSupport;
    }

}