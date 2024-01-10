package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.model.Bank;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class BankDAOImpl implements BankDAO{

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public Bank findUser(Long bankId) throws NoSuchComptException {
        try {
            Bank comptFind = (Bank) em.createQuery("Select compt from Bank compt where compt.bankId=:bankId").setParameter("bankId", bankId).getSingleResult();
            return comptFind;
        } catch (NoResultException e) {
            throw new NoSuchComptException();
        }
    }
    @Override
    public Bank findUserByNumero(String bankNumber) throws NoSuchComptException {
        try {
            Bank comptFind = (Bank) em.createQuery("Select compt from Bank compt where compt.bankNumber=:bankNumber").setParameter("bankNumber", bankNumber).getSingleResult();
            return comptFind;
        } catch (NoResultException e) {
            throw new NoSuchComptException();
        }
    }

    @Override
    public TransfertArgent updateTwoComptes(TransfertArgent transfertArgent) throws NoSuchComptException {
        try{
            Bank bankEmet = this.findUserByNumero(transfertArgent.getEmetteur().getUserNumeroBank());
            Bank bankRece = this.findUserByNumero(transfertArgent.getReceveur().getUserNumeroBank());
            double value = transfertArgent.getValue();
            if (bankEmet != null && bankRece != null){
                bankEmet.setBankAmonut(bankEmet.getBankAmonut() - value);
                bankRece.setBankAmonut(bankRece.getBankAmonut() + value);
                em.persist(bankEmet);
                em.persist(bankRece);
            }
        } catch (NoResultException e) {
            throw new NoSuchComptException();
        }
        return transfertArgent;
    }
    @Override
    public TransfertArgent updateCompteCredit(TransfertArgent transfertArgent) throws NoSuchComptException{
        try{
            Bank bankEmet = this.findUserByNumero(transfertArgent.getEmetteur().getUserNumeroBank());
            double value = transfertArgent.getValue();
            if (bankEmet != null){
                bankEmet.setBankAmonut(bankEmet.getBankAmonut() - value);
                em.persist(bankEmet);
            }
        } catch (NoResultException e) {
            throw new NoSuchComptException();
        }
        return transfertArgent;
    }

    @Override
    public TransfertArgent updateCompteDebit(TransfertArgent transfertArgent) throws NoSuchComptException {
        try{
            Bank bankRece = this.findUserByNumero(transfertArgent.getReceveur().getUserNumeroBank());
            double value = transfertArgent.getValue();
            if (bankRece != null){
                bankRece.setBankAmonut(bankRece.getBankAmonut() + value);
                em.persist(bankRece);
            }
        } catch (NoResultException e) {
            throw new NoSuchComptException();
        }
        return transfertArgent;
    }

}
