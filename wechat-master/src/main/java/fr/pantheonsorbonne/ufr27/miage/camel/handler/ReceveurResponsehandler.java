package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class ReceveurResponsehandler {
    @Handler
    public TransfertArgent sendReceveurMessage(Exchange exchange) {
        TransfertArgent transfertArgent = exchange.getMessage().getBody(TransfertArgent.class);
        exchange.getMessage().setHeader("login", transfertArgent.getLoginReceveur());
        return transfertArgent;

    }
}
