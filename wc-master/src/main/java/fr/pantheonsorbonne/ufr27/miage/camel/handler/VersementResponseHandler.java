package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class VersementResponseHandler {
    @Handler
    public void checkEmetteurAmountRest(Exchange exchange) {
        Versement versement = exchange.getMessage().getBody(Versement.class);
        System.out.println("Le versement commence : \n" +
                " - " +versement.getEmetteurId().getUserName() + " souhaite verser "+ versement.getVersementAmount()
                + " euros a "+ versement.getReceveurId().getUserName());

        if (versement.getEmetteurId().getUserWallet() >= versement.getVersementAmount()){
            exchange.getMessage().setHeader("success", true);
            exchange.getMessage().setBody(versement);
            System.out.println("Solde de Portefeuille est suffisant");
            return;
        }
        System.out.println("Solde de Portefeuille est insuffisant");
        exchange.getMessage().
                setHeader("success", false);
    }
}
