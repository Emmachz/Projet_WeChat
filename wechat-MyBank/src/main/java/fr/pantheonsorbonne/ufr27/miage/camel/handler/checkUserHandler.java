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
        if(!transfertArgent.getEmetteur().getUserNameBank().equals("MyBank")
                && !transfertArgent.getReceveur().getUserNameBank().equals("MyBank")){
            System.out.println("Le compte bancaire n'existe pas : Mauvais Nom de Banque !");
            exchange.getMessage().
                    setHeader("success", false);
        } else if (transfertArgent.getEmetteur().getUserNameBank().equals("MyBank")
                && transfertArgent.getReceveur().getUserNameBank().equals("MyBank")) {
            exchange.getMessage().
                    setHeader("success", true);
            exchange.getMessage().
                    setHeader("DoubleCompt", true);
        }else if (transfertArgent.getEmetteur().getUserNameBank().equals("MyBank")){
            exchange.getMessage().
                    setHeader("success", true);
            exchange.getMessage().
                    setHeader("emetteur", true);
        }else if (transfertArgent.getReceveur().getUserNameBank().equals("MyBank")){
            exchange.getMessage().
                    setHeader("success", true);
            exchange.getMessage().
                    setHeader("receveur", true);
        }

    }
}
