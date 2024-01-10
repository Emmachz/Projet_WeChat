package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotAllowedToPayException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;

public interface PurchaseService {
    public PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotFoundException;

    public void confirm(PurchaseConfirmation confirmationInfos) throws PurchaseNotExistException, UserNotAllowedToPayException;

    public PurchaseDTO findPurchase(int id) throws PurchaseNotExistException;

}
