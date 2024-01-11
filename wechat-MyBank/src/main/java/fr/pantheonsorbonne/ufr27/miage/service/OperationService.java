package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;

public interface OperationService {
    TransfertArgent realizeOperation (TransfertArgent transfertArgent) throws NoSuchComptException;

    void creditMoney(String bankNumber, double amount);

    void debitMoney(String bankNumber, double amount);

}
