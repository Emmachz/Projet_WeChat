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

import static io.netty.handler.codec.http.HttpHeaders.setHeader;

@ApplicationScoped
public class AlertGateway {

    @Inject
    AlertService alertService;

    @Inject
    CamelContext camelContext;

    public void addAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region(getRegionId(alert.getAlertRegion()),alert.getAlertRegion()));
        this.alertService.addAlert(newAlert);
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
        String regionId = getRegionId(alert.getAlertRegion());
        return new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region(regionId, alert.getAlertRegion()));
    }

    private String getRegionId(String regionName) {
        Map<String, String> regionNameMapping = new HashMap<>();
        regionNameMapping.put("auvergne-rhone-alpes", "FR-ARA");
        regionNameMapping.put("bourgogne-franche-comte", "FR-BFC");
        regionNameMapping.put("bretagne", "FR-BRE");
        regionNameMapping.put("corse", "FR-COR");
        regionNameMapping.put("centre-val-de-loire", "FR-CVL");
        regionNameMapping.put("grand-est", "FR-GES");
        regionNameMapping.put("hauts-de-france", "FR-HDF");
        regionNameMapping.put("ile-de-france", "FR-IDF");
        regionNameMapping.put("nouvelle-aquitaine", "FR-NAQ");
        regionNameMapping.put("normandie", "FR-NOR");
        regionNameMapping.put("occitanie", "FR-ACC");
        regionNameMapping.put("provence-alpes-cote-dazur", "FR-PAC");
        regionNameMapping.put("pays-de-la-loire", "FR-PDL");
        return regionNameMapping.get(regionName);
    }

    private void sendAlertToTopic(ProducerTemplate producerTemplate, Alert newAlert) {
        String topicName = "sjms2:topic:alert" + newAlert.getRegion().toLowerCase();
        producerTemplate.sendBodyAndHeader("direct:alerttransfert",
                new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),
                "headerRegion", newAlert.getRegion());
    }

}
