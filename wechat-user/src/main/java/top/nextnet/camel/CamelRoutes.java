package top.nextnet.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:inscription").marshal().json().process(exchange -> System.out.println(exchange.getIn().getBody()));
    }
}
