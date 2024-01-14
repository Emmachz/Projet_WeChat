package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientAmountInAccountException;
import fr.pantheonsorbonne.ufr27.miage.model.Bank;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BankDAOImpl implements BankDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public Bank findUser(Long bankId) throws NoSuchAccountException {
        try {
            Bank comptFind = (Bank) em.createQuery("Select compt from Bank compt where compt.bankId=:bankId").setParameter("bankId", bankId).getSingleResult();
            return comptFind;
        } catch (NoResultException e) {
            throw new NoSuchAccountException();
        }
    }
    @Override
    @Transactional
    public Bank findUserByNumero(String bankNumber) throws NoSuchAccountException {
        try {
            Bank comptFind = (Bank) em.createQuery("Select compt from Bank compt where compt.bankNumber=:bankNumber").setParameter("bankNumber", bankNumber).getSingleResult();
            return comptFind;
        } catch (NoResultException e) {
            throw new NoSuchAccountException();
        }
    }

    @Override
    public TransfertArgent updateTwoComptes(TransfertArgent transfertArgent) throws NoSuchAccountException {
        try{
            Bank bankEmet = this.findUserByNumero(transfertArgent.getEmetteur().getUserNumeroBank());
            Bank bankRece = this.findUserByNumero(transfertArgent.getReceveur().getUserNumeroBank());
            double value = transfertArgent.getValue();
            if (bankEmet != null && bankRece != null){
                bankEmet.setBankAmount(bankEmet.getBankAmonut() - value);
                bankRece.setBankAmount(bankRece.getBankAmonut() + value);
                em.persist(bankEmet);
                em.persist(bankRece);
            }
        } catch (NoResultException e) {
            throw new NoSuchAccountException();
        }
        return transfertArgent;
    }
    @Override
    public TransfertArgent updateCompteCredit(TransfertArgent transfertArgent) throws NoSuchAccountException {
        try{
            Bank bankEmet = this.findUserByNumero(transfertArgent.getEmetteur().getUserNumeroBank());
            double value = transfertArgent.getValue();
            if (bankEmet != null){
                bankEmet.setBankAmount(bankEmet.getBankAmonut() - value);
                em.persist(bankEmet);
            }
        } catch (NoResultException e) {
            throw new NoSuchAccountException();
        }
        return transfertArgent;
    }
    @Override
    public boolean checkSolde(String bankNumber, double value){
        try{
            Bank bankEmet = this.findUserByNumero(bankNumber);
            if (bankEmet.getBankAmonut() >= value){
                return true;
            }
        } catch (NoSuchAccountException e) {
            throw new RuntimeException(e);
        }
        return false;
    }



    @Override
    public TransfertArgent updateCompteDebit(TransfertArgent transfertArgent) throws NoSuchAccountException {
        try{
            Bank bankRece = this.findUserByNumero(transfertArgent.getReceveur().getUserNumeroBank());
            double value = transfertArgent.getValue();
            if (bankRece != null){
                bankRece.setBankAmount(bankRece.getBankAmonut() + value);
                em.persist(bankRece);
            }
        } catch (NoResultException e) {
            throw new NoSuchAccountException();
        }
        return transfertArgent;
    }

    @Override
    @Transactional
    public void addMoneyToAccount(String bankNumber, double amount) throws NoSuchAccountException
    {
        Bank account = this.findUserByNumero(bankNumber);
        double initialBalance = account.getBankAmonut();
        account.setBankAmount(initialBalance + amount);
        em.merge(account);
        em.flush();
    }

    @Override
    @Transactional
    public void debitMoneyFromAccount(String bankNumber, double amount) throws UnsufficientAmountInAccountException, NoSuchAccountException
    {
        Bank account = this.findUserByNumero(bankNumber);
        double initialBalance = account.getBankAmonut();
        if(initialBalance >= amount)
        {
            account.setBankAmount(initialBalance - amount);
        }
        else
        {
            throw new UnsufficientAmountInAccountException();
        }
        em.persist(account);
    }

}
