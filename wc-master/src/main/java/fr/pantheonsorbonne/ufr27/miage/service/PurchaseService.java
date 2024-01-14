package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.*;

public interface PurchaseService {
    public PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotExistingException;

    public void confirm(PurchaseConfirmation confirmationInfos) throws PurchaseNotExistException, UserNotAllowedToPayException, AlreadyPaidPurchaseException;

    public PurchaseDTO findPurchase(Long id) throws PurchaseNotExistException;

}
