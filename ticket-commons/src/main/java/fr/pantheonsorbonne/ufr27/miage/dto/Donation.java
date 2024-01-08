package fr.pantheonsorbonne.ufr27.miage.dto;

public class Donation {
    int donationId;
    String description;
    String region;
    Requires requires;


    public Donation(int donationId, String description, String region, Requires requires) {
        this.donationId = donationId;
        this.description = description;
        this.region = region;
        this.requires = requires;
    }

    public Donation() {
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Requires getRequires() {
        return requires;
    }

    public void setRequires(Requires requires) {
        this.requires = requires;
    }

}