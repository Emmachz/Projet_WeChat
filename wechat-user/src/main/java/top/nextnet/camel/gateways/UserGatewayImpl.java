package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class UserGatewayImpl implements UserGateway {

    @Inject
    CamelContext context;

    @Override
    public boolean sendTransfertInfos(TransfertArgent transfertArgent) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:versement", transfertArgent);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
