package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class AlertGateway {
    @Inject
    CamelContext camelContext;

    public void sendAlert(Event event) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            if(event.getRegion().equals("all")){
                producerTemplate.sendBody("direct:AlertAllRegion", new Alert(event.getIdEvent(), event.getDescription(), event.getRegion()));
            }
            else {
                producerTemplate.sendBody("direct:Alert", new Alert(event.getIdEvent(), event.getDescription(), event.getRegion()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
