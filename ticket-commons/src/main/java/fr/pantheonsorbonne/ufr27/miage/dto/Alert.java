package fr.pantheonsorbonne.ufr27.miage.dto;

public class Alert {

    int alertId;

    String alertDescription;

    String alertRegion;


    public Alert(int alertId, String alertDescription, String alertRegion) {

        this.alertId = alertId;
        this.alertDescription = alertDescription;
        this.alertRegion = alertRegion;
    }

    public Alert() {
    }

    public int getAlertId() {
        return alertId;
    }

    public String getAlertDescription(){
        return alertDescription;
    }

    public String getAlertRegion(){
        return alertRegion;
    }



}
