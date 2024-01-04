package fr.pantheonsorbonne.ufr27.miage.dto;

public class Giving {
    int donationId;
    String regionOfUser;
    String typeGive;
    int quantity;

    public Giving(int donationId, String regionOfUser, String typeGive, int quantity) {
        this.donationId = donationId;
        this.regionOfUser = regionOfUser;
        this.typeGive = typeGive;
        this.quantity = quantity;
    }

    public Giving() {
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getRegionOfUser() {
        return regionOfUser;
    }

    public void setVenueId(String regionOfUser) {
        this.regionOfUser = regionOfUser;
    }

    public String getTypeGive() {
        return typeGive;
    }

    public void setTypeGive(String typeGive) {
        this.typeGive = typeGive;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
