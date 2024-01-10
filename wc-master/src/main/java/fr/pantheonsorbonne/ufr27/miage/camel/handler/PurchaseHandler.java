package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.*;
import fr.pantheonsorbonne.ufr27.miage.service.PurchaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchaseHandler {

    @Inject
    PurchaseService service;

    public PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotExistingException {
        return service.init(purchase);
    }

    public void confirm(PurchaseConfirmation confirmationInfos) throws UserNotAllowedToPayException, PurchaseNotExistException
    {
        service.confirm(confirmationInfos);
    }


}

