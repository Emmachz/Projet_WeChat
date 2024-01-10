package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.VersementGateway;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {


    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.VersementResponseHandler versementResponseHandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.EmetteurResponsehandler emetteurResponsehandler;

    @Inject
    fr.pantheonsorbonne.ufr27.miage.camel.handler.ReceveurResponsehandler receveurResponsehandler;

    @Inject
    VersementGateway versementGateway;


    @Inject
    CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);
        from("sjms2:" + jmsPrefix + "TransfertArgent")
                .unmarshal().json(TransfertArgent.class)
                .bean(versementGateway, "findTwoUsersVersement")
                .bean(versementResponseHandler)
                .choice()
                    .when(header("success").isEqualTo(true))
                        .bean(versementGateway, "realizeVersementWallet")
                        .bean(emetteurResponsehandler)
                        .marshal().json()
                        .to("sjms2:topic:" + jmsPrefix + "versementSuccesEmetteur")
                        .stop()
                .otherwise()
                .bean(versementGateway, "sendInfosToBank")
                .marshal().json()
                .multicast()
                .to("sjms2:" + jmsPrefix + "MyBankSystem?exchangePattern=InOut", "sjms2:" + jmsPrefix + "YesBankSystem?exchangePattern=InOut")
                .parallelProcessing()
                .aggregationStrategy((new AggregationStrategy() {
                    @Override
                    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                        return null;
                    }
                }))


        ;




    }

}
