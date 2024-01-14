package fr.pantheonsorbonne.ufr27.miage.dto;

public class Giving {
    String userLogin;
    int donationId;
    int helpId;
    String typeGive;
    double quantity;
    UserLocal usergive;

    public Giving(int helpId, String userLogin, String typeGive, double quantity){
        this.userLogin = userLogin;
        this.helpId = helpId;
        this.typeGive = typeGive;
        this.quantity = quantity;
    }

    public Giving (UserLocal usergive, double quantity,  String typeGive, int donationId){
        this.usergive = usergive;
        this.quantity = quantity;
        this.donationId = donationId;
        this.typeGive = typeGive;
    }


    public Giving() {
    }
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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


    public UserLocal getUsergive() {
        return usergive;
    }

    public void setUsergive(UserLocal usergive) {this.usergive = usergive;}

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}