package top.nextnet.camel.gateways;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import top.nextnet.Model.UserInscript;

import java.io.IOException;

@ApplicationScoped
public class UserGatewayImpl implements top.nextnet.service.UserGateway{
    @Inject
    CamelContext context;

    @Override
    public void sendUserInfos(UserInscript userInscript) {
        try (ProducerTemplate producer = context.createProducerTemplate()) {
            producer.sendBody("direct:inscription", userInscript);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
