package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.Alert;

import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class AlertGateway {

    @Inject
    AlertService alertService;


    public void addAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion());
        this.alertService.addAlert(newAlert);
    }

    public void checkRegion(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion());
        this.alertService.checkRegion(newAlert);
    }

}
