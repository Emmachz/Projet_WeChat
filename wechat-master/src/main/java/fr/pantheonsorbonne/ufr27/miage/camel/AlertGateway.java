package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.AlertNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Region;

import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class AlertGateway {

    @Inject
    AlertService alertService;

    @Inject
    CamelContext camelContext;

    public void addAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        this.alertService.addAlert(alert);
    }

    public void addAlertAllRegion(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        this.alertService.addAlertAllRegion(alert);
    }

    public void transfertAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert) throws AlertNotFoundException, IOException {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            Alert newAlert = createNewAlert(alert);
            sendAlertToTopic(producerTemplate, newAlert);
        } catch (NonUniqueResultException | NoResultException e) {
            throw new AlertNotFoundException(alert.getAlertId());
        }
    }

    private Alert createNewAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert) {
        String regionId = this.alertService.getRegionId(alert.getAlertRegion());
        return new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region(regionId, alert.getAlertRegion()));
    }

    private void sendAlertToTopic(ProducerTemplate producerTemplate, Alert newAlert) {
        String topicName = "sjms2:topic:alert" + newAlert.getRegion().toLowerCase();
        producerTemplate.sendBodyAndHeader("direct:alerttransfert",
                new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),
                "headerRegion", newAlert.getRegion());
    }

}
