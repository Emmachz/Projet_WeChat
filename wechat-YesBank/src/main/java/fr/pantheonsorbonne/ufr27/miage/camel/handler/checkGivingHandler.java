package fr.pantheonsorbonne.ufr27.miage.camel.handler;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
@ApplicationScoped
public class checkGivingHandler {
    @Handler
    public void checkUserInfo(Exchange exchange) {
        Giving giving = exchange.getMessage().getBody(Giving.class);

        if (giving.getUserLogin() == null){
            exchange.getMessage().setHeader("success", true);
        }else {
            exchange.getMessage().setHeader("success", false);
            exchange.getMessage().setHeader("login", giving.getUserLogin());
        }
    }

}
