package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.*;

public interface PurchaseService {
    PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotExistingException;

    void confirm(PurchaseConfirmation confirmationInfos) throws PurchaseNotExistException, UserNotAllowedToPayException, AlreadyPaidPurchaseException;

    PurchaseDTO findPurchase(Long id) throws PurchaseNotExistException;

    BankOperation findBankCreditInfosFromPurchase(PurchaseDTO purchase);

    BankOperation findBankDebitInfosFromPurchase(PurchaseDTO purchase);

}
