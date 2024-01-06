package fr.pantheonsorbonne.ufr27.miage.services;

import fr.pantheonsorbonne.ufr27.miage.model.Account;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
public class AccountServiceImpl implements AccountService{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void creditMoney(int accountId, int amount) {
        try {
            Account account = em.find(Account.class, accountId);
            int initialBalance = account.getBalance();
            account.setBalance(initialBalance + amount);
            em.merge(account);
            em.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void debitMoney(int accountId, int amount) {
        Account account = em.find(Account.class, accountId);
        int initialBalance = account.getBalance();
        account.setBalance(initialBalance - amount);
        em.persist(account);
    }
}
