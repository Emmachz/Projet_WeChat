package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.CompteGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchAccountException;
import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.bankName")
    String bankName;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.checkUserHandler checkUserHandler;

    @Inject
    CompteGateway accountGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        onException(NoSuchAccountException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("Le compte bancaire n existe pas : Mauvais Nom de Banque !"));

        from("sjms2:" + jmsPrefix + "MyBankSystem?exchangePattern=InOut")
                .unmarshal().json(TransfertArgent.class)
                .bean(accountGateway, "realizeOperation")
                .bean(checkUserHandler)
                .choice()
                .when(header("success").isEqualTo(false))
                .log("Le compte bancaire n existe pas : Mauvais Nom de Banque !")
                .marshal().json()
                .stop()
                .otherwise()
                .log("Versement termine : success")
                .marshal().json()
                .stop()
                .end()
        ;

        from("sjms2:" + jmsPrefix + "bank-debit?exchangePattern=InOut")
                .filter(exchange -> exchange.getMessage().getBody(BankOperation.class).getBankName() != bankName)
                .bean(accountGateway, "debit");

        from("sjms2:" + jmsPrefix + "bank-credit?exchangePattern=InOut")
                .filter(exchange -> exchange.getMessage().getBody(BankOperation.class).getBankName() != bankName)
                .bean(accountGateway, "credit");

    }

}
