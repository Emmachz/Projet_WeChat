package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;
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
    public Giving upadateUserGiving (Giving give){
        try{
            User emetteur = this.findUserByLogin(give.getUsergive().userLogin());
            double value = give.getQuantity();
            if (emetteur != null){
                emetteur.setUserWallet(emetteur.getUserWallet() - value);
                em.persist(emetteur);
                return give;
            }
        } catch (UserNotFoundException.NoExistUserException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
