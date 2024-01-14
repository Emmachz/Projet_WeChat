package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;

public interface VersementService {

    Versement findTwoUsersVersement (TransfertArgent transfertArgent);

    TransfertArgent initTransfer(TransfertArgent transfertArgent) throws UserNotFoundException.NoExistUserException;

    BankOperation findBankDebitInfosFromTransfer(TransfertArgent transfer);

    BankOperation findBankDebitInfosFromPurchase(PurchaseDTO purchase);

    BankOperation findBankCreditInfosFromPurchase(PurchaseDTO purchase);

    TransfertArgent realizeVersementWallet (Versement versement);

    TransfertArgent sendInfosToBank(Versement versement);
}
