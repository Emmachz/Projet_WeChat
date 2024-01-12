package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Collection;

public class Donation {
    int donationId;
    String description;
    //String region;
    Collection<Help> helps;


    public Donation(int donationId, String description,  Collection<Help> helps) {
        this.donationId = donationId;
        this.description = description;
        /*
        this.region = region;
         */
        this.helps = helps;
    }

    public Donation() {
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }
/*
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

 */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Help> getHelps() {
        return helps;
    }

    public void setHelps(Collection<Help> helps) {
        this.helps = helps;
    }

}