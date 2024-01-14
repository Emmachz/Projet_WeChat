package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;

public interface VersementService {

    Versement findTwoUsersVersement (TransfertArgent transfertArgent) throws UserNotFoundException.NoExistUserException;

    TransfertArgent realizeVersementWallet (Versement versement);

    TransfertArgent sendInfosToBank(Versement versement);
}
