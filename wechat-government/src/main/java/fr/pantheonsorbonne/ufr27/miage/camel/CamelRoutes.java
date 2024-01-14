package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Override
    public void configure() {

        camelContext.setTracing(true);

        from ("direct:Alert")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "sendAlert");

        from ("direct:AlertAllRegion")
                .marshal().json()
                .to("sjms2:" + jmsPrefix + "sendAlertAllRegion");
    }
}
