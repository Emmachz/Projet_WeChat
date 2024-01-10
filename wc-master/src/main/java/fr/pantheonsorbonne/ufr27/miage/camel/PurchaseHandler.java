package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotAllowedToPayException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.PurchaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchaseHandler {

    @Inject
    PurchaseService service;

    public PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotFoundException {
        return service.init(purchase);
    }

    public void confirm(PurchaseConfirmation confirmationInfos) throws UserNotAllowedToPayException, PurchaseNotExistException
    {
        service.confirm(confirmationInfos);
    }


}

