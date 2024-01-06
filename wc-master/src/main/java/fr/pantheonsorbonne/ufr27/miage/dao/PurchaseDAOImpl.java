package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Purchase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PurchaseDAOImpl implements PurchaseDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void createPurchase(int idES, int idWC, int amount) {
        var purchase = new Purchase(idES, idWC, amount);
        this.em.persist(purchase);
        this.em.flush();
    }

    @Override
    public void confirmPurchase(int id) {

    }
}
