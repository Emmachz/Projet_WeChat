package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class checkUserHandler {

    @Handler
    public void checkUserInfo(Exchange exchange) {
        TransfertArgent transfertArgent = exchange.getMessage().getBody(TransfertArgent.class);

        if (transfertArgent.getLoginEmetteur() != null){
            exchange.getMessage().setHeader("success", false);
            exchange.getMessage().setHeader("emetteur", transfertArgent.getLoginEmetteur());
        }else {
            exchange.getMessage().setHeader("success", true);
            exchange.getMessage().setHeader("emetteur", transfertArgent.getEmetteur().userLogin());
        }
    }
}
