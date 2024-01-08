
package fr.pantheonsorbonne.ufr27.miage.dto;

public class Requires {
    double moneySupport;
    double timeSupport;
    double clotheSupport;

    public Requires(double moneySupport, double timeSupport, double clotheSupport) {
        this.moneySupport = moneySupport;
        this.timeSupport = timeSupport;
        this.clotheSupport = clotheSupport;
    }

    public Requires() {
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