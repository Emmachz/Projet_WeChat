package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import top.nextnet.service.PurshasingGateway;

import java.io.IOException;

@ApplicationScoped
public class PurchasingGatewayImpl implements PurshasingGateway {

    @Inject
    CamelContext context;

    @Override
    public void sendWeChatPurshasing(String weChatUser, String externalSeller, double amount) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:selling", new PurchaseDTO(weChatUser, externalSeller, amount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
