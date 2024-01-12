package fr.pantheonsorbonne.ufr27.miage.dto;

public class Giving {
    int userId;
    int donationId;
    int helpId;
    String typeGive;
    int quantity;

    public Giving(int userId, int donationId,  int helpId, String typeGive, int quantity) {
        this.userId = userId;
        this.donationId = donationId;
        this.helpId = helpId;
        this.typeGive = typeGive;
        this.quantity = quantity;
    }

    public Giving() {
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getHelpId() {
        return helpId;
    }

    public void setHelpId(int helpId) {
        this.helpId = helpId;
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