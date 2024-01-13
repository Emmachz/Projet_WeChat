package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class RegionGateway {
    @Inject
    AlertService alertService;

    @Inject
    CamelContext camelContext;

    public void sendAlert(Event event) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            //producerTemplate.sendBody("direct:Region", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(event.getIdEvent(), event.getDescription(), event.getRegion()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
