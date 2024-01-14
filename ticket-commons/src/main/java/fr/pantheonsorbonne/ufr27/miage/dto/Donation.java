package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Collection;

public class Donation {
    int donationId;

    String description;

    String regionOfNeed;

    double moneySupport;

    double timeSupport;

    double clotheSupport;

    double moneyGived;

    double timeGived;

    double clotheGived;





    public Donation(int donationId, String description, String regionOfNeed, double moneySupport, double timeSupport, double clotheSupport, double moneyGived, double timeGived, double clotheGived) {

        this.donationId = donationId;
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

    public int getDonationId() {
        return donationId;
    }

    public String getDescription(){
        return description;
    }

    public String getRegionOfNeed(){
        return regionOfNeed;
    }

    public double getMoneySupport() {
        return moneySupport;
    }

    public double getTimeSupport(){
        return timeSupport;
    }

    public double getClotheSupport(){
        return clotheSupport;
    }

    public double getMoneyGived() {
        return moneyGived;
    }

    public double getTimeGived(){
        return timeGived;
    }

    public double getClotheGived(){
        return clotheGived;
    }
}
