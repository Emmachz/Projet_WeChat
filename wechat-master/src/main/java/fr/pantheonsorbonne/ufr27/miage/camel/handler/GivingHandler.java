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
            exchange.getMessage().setHeader("typeGive", give.getTypeGive());
            exchange.getMessage().setHeader("login", give.getUserLogin());
            if(give.getUsergive().wallet() >= give.getQuantity()){
                exchange.getMessage().setHeader("success", true);
            }else {
                exchange.getMessage().setHeader("success", "passBank");
                exchange.getMessage().setHeader("bank", give.getUsergive().userNameBank());
            }
    }
}
