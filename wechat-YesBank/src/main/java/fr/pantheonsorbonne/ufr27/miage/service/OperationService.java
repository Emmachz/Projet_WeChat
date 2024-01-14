package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchAccountException;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientAmountInAccountException;

public interface OperationService {
    TransfertArgent realizeOperation (TransfertArgent transfertArgent) throws NoSuchAccountException;

    void creditMoney(String bankNumber, double amount) throws NoSuchAccountException;

    void debitMoney(String bankNumber, double amount) throws UnsufficientAmountInAccountException, NoSuchAccountException;

}
