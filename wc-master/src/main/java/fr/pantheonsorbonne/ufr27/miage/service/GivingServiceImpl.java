package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.camel.gateway.DonationGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaDonationException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
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

    @Inject
    DonationGateway donationGateway;

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Giving giveMoney(Giving give)throws UnsuficientQuotaDonationException {
        try {
            Donation donation = callDonation(give);
            donation.setMoneyGived(donation.getMoneyGived() + give.getQuantity());
            em.persist(donation);
            if (donation.getMoneyGived()>donation.getMoneySupport()){
                donationGateway.updateDonation(donation);
            }
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "money");
        }
       return userDAO.upadateUserGiving(give);
    }

    @Override
    @Transactional
    public Giving updateDonationAfterBank (Giving give) throws UnsuficientQuotaDonationException {
        try {
            Donation donation = callDonation(give);
            donation.setMoneyGived(donation.getMoneyGived() + give.getQuantity());
            em.persist(donation);
            if (callDonation(give).getMoneyGived()>callDonation(give).getMoneySupport()){
                donationGateway.updateDonation(donation);
            }
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "money");
        }
        return give;
    }

    @Override
    @Transactional
    public Giving giveTime(Giving give) throws UnsuficientQuotaDonationException{
        try {
            Donation donation = callDonation(give);
            donation.setTimeGived(donation.getTimeGived() + give.getQuantity());
            em.persist(donation);
            if (donation.getTimeGived()>donation.getTimeSupport()){
                donationGateway.updateDonation(donation);
            }
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "time");
        }
        return give;
    }

    @Override
    @Transactional
    public Giving giveClothe(Giving give) throws UnsuficientQuotaDonationException{
        try {
            Donation donation = callDonation(give);
            donation.setClotheGived(donation.getClotheGived() + give.getQuantity());
            em.persist(donation);
            if (donation.getClotheGived()>donation.getClotheSupport()){
                donationGateway.updateDonation(donation);
            }
        } catch (NonUniqueResultException | NoResultException e) {
            throw new UnsuficientQuotaDonationException(give.getQuantity(), "clothe");
        }
        return give;
    }

    @Override
    @Transactional
    public Giving convertirGiving(Giving give) throws UserNotFoundException.NoExistUserException {
            User giver = userDAO.findUserByLogin(give.getUserLogin());
            UserLocal user = new UserLocal(giver.getUserName(), giver.getUserWallet(),giver.getUserLogin(), giver.getUserRegion().getIdRegion(), giver.getUserEmail(), giver.getUserNameBank(), giver.getUserNumeroBank());
            return new Giving(user, give.getQuantity(), give.getTypeGive(), give.getHelpId());
    }

    private Donation callDonation(Giving give){
        return  em.createQuery("select d from  Donation d  where d.donationId =: id", Donation.class).setParameter("id", give.getDonationId()).getSingleResult();

    }




}