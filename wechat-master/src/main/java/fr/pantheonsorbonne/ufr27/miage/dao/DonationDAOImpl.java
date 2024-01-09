package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.dto.Require;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class DonationDAOImpl implements DonationDAO {


    @PersistenceContext
    EntityManager em;



    @Override
    public Donation findDonation(int transitionalDonationId) throws NoSuchTicketException {
        Donation t = em.find(Donation.class, transitionalDonationId);
        if (null == t) {
            throw new NoSuchTicketException();
        }
        return t;
    }

    @Override
    @Transactional
    public Donation giveDonation(int transitionalTickerId, String typeGive, int quantity) {
        Donation d = em.find(Donation.class, transitionalTickerId);
        Collection<Require> requires = d.getRequires();
        for (Require require : requires ) {
            if(typeGive.equals("money")){
                require.setMoneySupport(require.getMoneySupport()-quantity);
            } else if (typeGive.equals("clothe")) {
                require.setClotheSupport(require.getClotheSupport()-quantity);
            }else {
                require.setTimeSupport(require.getTimeSupport()-quantity);
            }
        }
        /*if (t != null) {*/

        d.setRequires(requires);
        return d;

    }

    @Override
    @Transactional
    public void removeDonation(int donationId) {
        Donation t = em.find(Donation.class, donationId);
        if (t != null) {
            em.remove(t);
        }
    }


}