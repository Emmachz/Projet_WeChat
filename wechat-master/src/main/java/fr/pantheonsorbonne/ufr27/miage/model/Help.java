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
    private int moneySupport;

    @Column(name = "timeSupport", nullable = false)
    private int timeSupport;

    @Column(name = "clotheSupport", nullable = false)
    private int clotheSupport;



    public Help(int helpId, String region, int moneySupport, int timeSupport, int clotheSupport) {
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

    public Integer getMoneySupport() {
        return moneySupport;
    }

    public void setMoneySupport(Integer moneySupport) {
        this.moneySupport = moneySupport;
    }

    public Integer getTimeSupport() {
        return timeSupport;
    }

    public void setTimeSupport(Integer timeSupport) {
        this.timeSupport = timeSupport;
    }

    public Integer getClotheSupport() {
        return clotheSupport;
    }

    public void setClotheSupport(Integer clotheSupport) {
        this.clotheSupport = clotheSupport;
    }

}