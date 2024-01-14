package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.AlreadyPaidPurchaseException;
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
    public Purchase createPurchase(String loginSeller, String loginUser, double amount) throws SellerNotRegisteredException, UserNotExistingException {
        var externalSeller = (ExternalSeller) em.createQuery("Select seller from ExternalSeller seller where seller.loginSeller=:login_seller").setParameter("login_seller", loginSeller).getSingleResult();
        if(externalSeller == null)
        {
            throw new SellerNotRegisteredException();
        }
        var weChatUser = (User) em.createQuery("Select user from User user where user.userLogin=:userLogin").setParameter("userLogin", loginUser).getSingleResult();;
        if(weChatUser == null)
        {
            throw new UserNotExistingException();
        }
        var purchase = new Purchase(externalSeller, weChatUser, amount);
        this.em.persist(purchase);
        this.em.flush();
        return purchase;
    }

    @Transactional
    @Override
    public void confirmPurchase(Long id) throws PurchaseNotExistException, AlreadyPaidPurchaseException{
        var purchase = this.findPurchase(id);
        if (purchase.isValidatedByUser())
        {
            throw new AlreadyPaidPurchaseException();
        }
        purchase.setValidatedByUser(true);
        this.em.merge(purchase);
        this.em.flush();
    }

    @Override
    @Transactional
    public Purchase findPurchase(Long id) throws PurchaseNotExistException
    {
        var purchase = this.em.find(Purchase.class, id);
        if(purchase == null)
        {
            throw new PurchaseNotExistException();
        }
        return purchase;
    }
}
