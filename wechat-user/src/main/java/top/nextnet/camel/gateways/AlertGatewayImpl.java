package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;
import java.util.Collection;

public class AlertGatewayImpl {
    @Inject
    CamelContext camelContext;

    /*
    public Collection<Alert> sendAlert() {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            return producerTemplate.sendBody("direct:getAllAlert");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    */

}
