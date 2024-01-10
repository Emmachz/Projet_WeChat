package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.model.Bank;

public interface BankDAO {

    Bank findUser(Long bankId) throws NoSuchComptException;
    Bank findUserByNumero(String bankNumber) throws NoSuchComptException;

    TransfertArgent updateTwoComptes(TransfertArgent transfertArgent) throws NoSuchComptException;
    TransfertArgent updateCompteCredit(TransfertArgent transfertArgent) throws NoSuchComptException;
    TransfertArgent updateCompteDebit(TransfertArgent transfertArgent) throws NoSuchComptException;
}
