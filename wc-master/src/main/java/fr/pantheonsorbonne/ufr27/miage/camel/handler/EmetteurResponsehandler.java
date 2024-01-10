package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class EmetteurResponsehandler {
    @Handler
    public TransfertArgent sendEmetteurMessage(Exchange exchange) {
        TransfertArgent transfertArgent = exchange.getMessage().getBody(TransfertArgent.class);
        exchange.getMessage().setHeader("login", transfertArgent.getEmetteur().getUserLogin());
        return transfertArgent;

    }
}
