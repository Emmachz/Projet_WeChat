package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.CompteGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.checkUserHandler checkUserHandler;

    @Inject
    CompteGateway compteGateway;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {

        camelContext.setTracing(true);

        onException(NoSuchComptException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("Le compte bancaire n existe pas : Mauvais Nom de Banque !"));

        from("sjms2:" + jmsPrefix + "MyBankSystem")
                .unmarshal().json(TransfertArgent.class)
                .bean(compteGateway, "realizeOperation")
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
                ;

    }

}
