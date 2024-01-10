package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BankDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.model.Bank;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OperationServiceImpl implements OperationService{
    @Inject
    BankDAO bankDAO;
    @Override
    @Transactional
    public TransfertArgent updateTwoComptesBank(TransfertArgent transfertArgent){
        try{
            bankDAO.updateTwoComptes(transfertArgent);
            return transfertArgent;
        }catch (NoSuchComptException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional
    public TransfertArgent updateCompteBankCredit(TransfertArgent transfertArgent){
        try{
            bankDAO.updateCompteCredit(transfertArgent);
            return transfertArgent;
        }catch (NoSuchComptException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional
    public TransfertArgent updateCompteBankDebit(TransfertArgent transfertArgent){
        try{
            bankDAO.updateCompteDebit(transfertArgent);
            return transfertArgent;
        }catch (NoSuchComptException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void creditMoney(String bankNumber, double amount) {
        bankDAO.addMoneyToAccount(bankNumber, amount);
    }

    @Override
    public void debitMoney(String bankNumber, double amount) {
        bankDAO.debitMoneyFromAccount(bankNumber, amount);
    }



}
