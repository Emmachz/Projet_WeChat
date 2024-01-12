package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class GivingHandler {

    @Handler
    public void checkTypeOfGive(Exchange exchange) {
            Giving give = exchange.getMessage().getBody(Giving.class);

            exchange.getMessage().
                    setHeader("typeGive", give.getTypeGive());
    }
}
