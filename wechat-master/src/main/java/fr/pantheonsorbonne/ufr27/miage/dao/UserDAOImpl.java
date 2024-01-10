package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public User findUser(Long userId) throws NoSuchUserException {
        try {
            User userFind = (User) em.createQuery("Select user from User user where user.userId=:userId").setParameter("userId", userId).getSingleResult();
            return userFind;
        } catch (NoResultException e) {
            throw new NoSuchUserException();
        }
    }

    @Override
    public User findUserByLogin(String userLogin) throws UserNotFoundException.NoExistUserException {
        try {
            User userFind = (User) em.createQuery("Select user from User user where user.userLogin=:userLogin").setParameter("userLogin", userLogin).getSingleResult();
            return userFind;
        } catch (NoResultException e) {
            throw new UserNotFoundException.NoExistUserException(userLogin);
        }
    }

    @Override
    @Transactional
    public void removeUser(Long userId) {
        User u = em.find(User.class, userId);
        if (u != null) {
            em.remove(u);
        }
    }
    @Override
    public TransfertArgent upadateUser (Versement versement){
        try{
            User emetteur = em.find(User.class, versement.getEmetteurId().getUserId());
            User receveur = em.find(User.class, versement.getReceveurId().getUserId());
            double value = versement.getVersementAmount();
            if (emetteur != null && receveur != null){
                emetteur.setUserWallet(emetteur.getUserWallet() - value);
                receveur.setUserWallet(receveur.getUserWallet() + value);
                em.persist(emetteur);
                em.persist(receveur);
                UserLocal emetteurLocal = new UserLocal(emetteur.getUserName(), emetteur.getUserLogin(), emetteur.getUserEmail(), emetteur.getUserNameBank(), emetteur.getUserNumeroBank());
                UserLocal receveurLocal = new UserLocal(receveur.getUserName(), receveur.getUserLogin(), receveur.getUserEmail(), receveur.getUserNameBank(), receveur.getUserNumeroBank());
                return new TransfertArgent(emetteurLocal,receveurLocal, value);
            }
        } catch (NoResultException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
