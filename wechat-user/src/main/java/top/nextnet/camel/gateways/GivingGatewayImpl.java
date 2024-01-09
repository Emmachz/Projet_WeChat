package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class GivingGatewayImpl implements top.nextnet.service.GivingGateway {

    @Inject
    CamelContext context;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userRegion")
    String userRegion;



    @Override
    public void sendGivingOrder(int donationId, String region, String typeGive, int quantity) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:cli", new Giving(donationId, userRegion, typeGive, quantity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
