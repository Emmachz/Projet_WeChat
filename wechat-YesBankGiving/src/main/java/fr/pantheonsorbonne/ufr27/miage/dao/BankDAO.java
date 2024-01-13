package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.model.Bank;


public interface BankDAO {

    Bank findUser(Long bankId) throws NoSuchComptException;

    Bank findUserByNumero(String bankNumber) throws NoSuchComptException;
    Giving updateGivingComptes (Giving giving) throws NoSuchComptException;

    boolean checkSolde(String bankNumber, double value);


}
