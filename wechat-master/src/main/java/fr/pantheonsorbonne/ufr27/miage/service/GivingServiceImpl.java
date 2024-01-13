package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaDonationException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Help;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GivingServiceImpl implements GivingService {
    @Inject
    UserDAO userDAO;

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Giving giveMoney(Giving give)throws UnsuficientQuotaDonationException {
        try {
            Help help = (Help) (em.createQuery("select h from  Help h  where h.helpId =: helpId").setParameter("helpId", give.getHelpId()).getSingleResult());
            help.setMoneySupport(help.getMoneySupport() - give.getQuantity());
            em.persist(help);
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "money");
        }
       return userDAO.upadateUserGiving(give);
    }

    @Override
    @Transactional
    public Giving giveTime(Giving give) throws UnsuficientQuotaDonationException{
        try {
            Help help = (Help) (em.createQuery("select h from  Help h  where h.helpId =: helpId").setParameter("helpId", give.getHelpId()).getSingleResult());
            help.setTimeSupport(help.getTimeSupport() - give.getQuantity());
            em.persist(help);
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "time");
        }
        return give;
    }

    @Override
    @Transactional
    public Giving giveClothe(Giving give) throws UnsuficientQuotaDonationException{
        try {
            Help help = (Help) (em.createQuery("select h from  Help h  where h.helpId =: helpId").setParameter("helpId", give.getHelpId()).getSingleResult());
            help.setClotheSupport(help.getClotheSupport() - give.getQuantity());
            em.persist(help);
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "clothes");
        }
        return give;
    }

    @Override
    @Transactional
    public Giving convertirGiving(Giving give) throws UserNotFoundException.NoExistUserException {
            User donneur = userDAO.findUserByLogin(give.getUserLogin());
            UserLocal user = new UserLocal(donneur.getUserName(), donneur.getUserWallet(),donneur.getUserLogin(), donneur.getUserRegion().getIdRegion(), donneur.getUserEmail(), donneur.getUserNameBank(), donneur.getUserNumeroBank());
            return new Giving(user, give.getQuantity(), give.getTypeGive(), give.getHelpId());
    }


}