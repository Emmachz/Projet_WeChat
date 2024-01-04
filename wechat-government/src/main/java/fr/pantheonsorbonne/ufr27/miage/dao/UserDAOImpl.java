package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserDAOImpl implements UserDAO{

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
    @Transactional
    public void removeUser(Long userId) {
        User u = em.find(User.class, userId);
        if (u != null) {
            em.remove(u);
        }
    }

    @Override
    @Transactional
    public User createNewUser(String userName, String userEmail, String userRegion, String userPassword) {
        User u = new User(userName, userEmail, userRegion, userPassword);
        em.persist(u);
        return u;
    }
}
