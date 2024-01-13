package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
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
    public Giving updateGivingComptes (Giving giving) throws NoSuchComptException {
        try {
            Bank compte = this.findUserByNumero(giving.getUsergive().userNumeroBank());
            double value = giving.getQuantity();
            if (compte != null) {
                compte.setBankAmonut(compte.getBankAmonut() - value);
                em.persist(compte);
            }
        } catch (NoResultException e) {
            throw new NoSuchComptException();
        }
        return giving;
    }

    @Override
    public boolean checkSolde(String bankNumber, double value){
        try{
            Bank bankEmet = this.findUserByNumero(bankNumber);
            if (bankEmet.getBankAmonut() >= value){
                return true;
            }
        } catch (NoSuchComptException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
