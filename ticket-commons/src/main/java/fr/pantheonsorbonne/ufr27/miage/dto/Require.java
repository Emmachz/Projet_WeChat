
package fr.pantheonsorbonne.ufr27.miage.dto;

public class Require {

    String region;
    double moneySupport;
    double timeSupport;
    double clotheSupport;

    public Require(String region, double moneySupport, double timeSupport, double clotheSupport) {
        this.region = region;
        this.moneySupport = moneySupport;
        this.timeSupport = timeSupport;
        this.clotheSupport = clotheSupport;
    }

    public Require() {
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