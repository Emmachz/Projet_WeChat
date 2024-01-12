package fr.pantheonsorbonne.ufr27.miage.dto;

public class Giving {
    int donationId;
    String userRegion;
    String typeGive;
    int quantity;

    public Giving(int donationId, String userRegion, String typeGive, int quantity) {
        this.donationId = donationId;
        this.userRegion = userRegion;
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

    public String getuserRegion() {
        return userRegion;
    }

    public void setuserRegion(String userRegion) {
        this.userRegion = userRegion;
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