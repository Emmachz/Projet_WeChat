package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class AggregationBankResponse implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(newExchange == null){
            System.out.println("je suis nullll");
        }
        if (newExchange.getIn().getHeader("DoubleCompt").equals(true)){
            TransfertArgent transfertArgent = newExchange.getIn().getBody(TransfertArgent.class);
            newExchange.getIn().setHeader("login", transfertArgent.getEmetteur().getUserLogin());
            return newExchange;
        }else if (oldExchange.getIn().getHeader("DoubleCompt").equals(true)){
            TransfertArgent transfertArgent = oldExchange.getIn().getBody(TransfertArgent.class);
            oldExchange.getIn().setHeader("login", transfertArgent.getEmetteur().getUserLogin());
            return oldExchange;
        } else if (oldExchange.getIn().getHeader("emetteur").equals(true) && newExchange.getIn().getHeader("receveur").equals(true) ||
                oldExchange.getIn().getHeader("receveur").equals(true) && newExchange.getIn().getHeader("emetteur").equals(true)) {
            TransfertArgent transfertArgent = newExchange.getIn().getBody(TransfertArgent.class);
            newExchange.getMessage().setHeader("login", transfertArgent.getEmetteur().getUserLogin());
            return newExchange;
        }
        return null;
    }
}
