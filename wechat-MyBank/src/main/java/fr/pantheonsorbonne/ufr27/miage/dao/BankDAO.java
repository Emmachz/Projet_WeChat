package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientAmountInAccountException;
import fr.pantheonsorbonne.ufr27.miage.model.Bank;

public interface BankDAO {

    Bank findUser(Long bankId) throws NoSuchAccountException;
    Bank findUserByNumero(String bankNumber) throws NoSuchAccountException;

    TransfertArgent updateTwoComptes(TransfertArgent transfertArgent) throws NoSuchAccountException;
    TransfertArgent updateCompteCredit(TransfertArgent transfertArgent) throws NoSuchAccountException;
    TransfertArgent updateCompteDebit(TransfertArgent transfertArgent) throws NoSuchAccountException;
    boolean checkSolde(String bankNumber, double value);

    void addMoneyToAccount(String bankNumber, double amount) throws NoSuchAccountException;

    void debitMoneyFromAccount(String bankNumber, double amount) throws UnsufficientAmountInAccountException, NoSuchAccountException;

    Giving updateGivingComptes(Giving giving) throws NoSuchAccountException;
}
