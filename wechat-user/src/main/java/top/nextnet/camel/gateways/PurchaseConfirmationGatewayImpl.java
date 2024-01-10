package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import top.nextnet.service.PurchaseConfirmationGateway;

import java.io.IOException;

@ApplicationScoped
public class PurchaseConfirmationGatewayImpl implements PurchaseConfirmationGateway {

    @Inject
    CamelContext context;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.user_mail")
    String mailUser;

    @Override
    public void confirmWeChatPurchase(int idPurchaseWeChat) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:confirm-purchase", new PurchaseConfirmation(idPurchaseWeChat, this.mailUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
