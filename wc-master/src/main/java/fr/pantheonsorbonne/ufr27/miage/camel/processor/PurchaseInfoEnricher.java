package fr.pantheonsorbonne.ufr27.miage.camel.processor;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.service.PurchaseService;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PurchaseInfoEnricher implements Processor {

    @Inject
    PurchaseService service;

    @Override
    public void process(Exchange exchange) throws Exception {
        PurchaseConfirmation confirmation = exchange.getMessage().getBody(PurchaseConfirmation.class);
        try {
            PurchaseDTO dto = this.service.findPurchase(confirmation.getPurchaseId());
            exchange.getMessage().setBody(dto);
        } catch (PurchaseNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}
