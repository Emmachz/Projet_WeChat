package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dao.MessageDAOImpl;
import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Message;
import fr.pantheonsorbonne.ufr27.miage.service.MessageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MessageGeteway {

    @Inject
    MessageService messageService;

    @Inject
    MessageDAOImpl messageDAO;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void message(Message message, @Header("idUser") int id, Exchange exchange){
        this.messageService.checkMessage(message, id);
        //exchange.
    }
}
