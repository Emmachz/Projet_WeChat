package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class GivingGatewayImpl implements GivingGateway {

    @Inject
    CamelContext context;



    @Override
    public void sendGivingOrder(Giving giving) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:giving", giving);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
