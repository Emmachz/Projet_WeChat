package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExternalSeller;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExternalSellerDAOImpl implements ExternalSellerDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public ExternalSeller findSellerByLogin(String login) throws SellerNotRegisteredException {
        try {
            ExternalSeller externalSeller = (ExternalSeller) em.createQuery("Select seller from ExternalSeller seller where seller.loginSeller=:login_seller").setParameter("login_seller", login).getSingleResult();
            return externalSeller;
        } catch (NoResultException e) {
            throw new SellerNotRegisteredException();
        }
    }
}
