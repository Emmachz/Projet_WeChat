package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;

public interface OperationService {
    TransfertArgent updateTwoComptesBank(TransfertArgent transfertArgent);
    TransfertArgent updateCompteBankCredit(TransfertArgent transfertArgent);
    TransfertArgent updateCompteBankDebit(TransfertArgent transfertArgent);

    void creditMoney(String bankNumber, double amount);

    void debitMoney(String bankNumber, double amount);

}
