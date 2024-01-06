package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.service.PurchaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchaseHandler {

    @Inject
    PurchaseService service;

    public PurchaseDTO init(PurchaseDTO purchase) {
        return service.init(purchase);
    }


}

