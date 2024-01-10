package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotExistingException;
import fr.pantheonsorbonne.ufr27.miage.model.ExternalSeller;
import fr.pantheonsorbonne.ufr27.miage.model.Purchase;
import fr.pantheonsorbonne.ufr27.miage.model.User;
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
    public void createPurchase(int idES, int idWC, int amount) throws SellerNotRegisteredException, UserNotExistingException {
        var externalSeller = em.find(ExternalSeller.class, idES);
        if(externalSeller == null)
        {
            throw new SellerNotRegisteredException();
        }
        var weChatUser = em.find(User.class, idWC);
        if(weChatUser == null)
        {
            throw new UserNotExistingException(idWC);
        }
        var purchase = new Purchase(externalSeller, weChatUser, amount);
        this.em.persist(purchase);
        this.em.flush();
    }

    @Transactional
    @Override
    public void confirmPurchase(int id) throws PurchaseNotExistException{
        var purchase = this.findPurchase(id);
        purchase.setValidatedByUser(true);
        this.em.merge(purchase);
        this.em.flush();
    }

    @Override
    public Purchase findPurchase(int id) throws PurchaseNotExistException
    {
        var purchase = this.em.find(Purchase.class, id);
        if(purchase == null)
        {
            throw new PurchaseNotExistException();
        }
        return purchase;
    }
}
