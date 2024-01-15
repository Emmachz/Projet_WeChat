package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class DonationDAOImpl implements DonationDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Collection<Donation> getDonations() {
        return em.createQuery("SELECT donation FROM Donation donation", Donation.class).getResultList();
    }


    @Override
    @Transactional
    public Donation addDonation(String RegionOfNeed, String description, double moneySupport, double timeSupport, double clotheSupport ) {

        Donation donation = new Donation();
        donation.setDescription(description);
        donation.setRegionOfNeed(RegionOfNeed);
        donation.setMoneySupport(moneySupport);
        donation.setTimeSupport(timeSupport);
        donation.setClotheSupport(clotheSupport);
        em.persist(donation);
        return donation;

    }

    @Override
    @Transactional
    public void updateDonation(Donation updateDonation){
        Donation donation = em.find(Donation.class, updateDonation.getId());
        if (donation != null) {
            donation.setMoneyGived(updateDonation.getMoneyGived());
            donation.setClotheGived(updateDonation.getClotheGived());
            donation.setTimeGived(updateDonation.getTimeGived());
            em.persist(donation);
        }

    }



    @Override
    @Transactional
    public void deleteDonation(int id){
        Donation donationToDelete = getDonationById(id);
        if (donationToDelete != null) {
            em.remove(donationToDelete);
        } else {
            System.out.println("La donation avec l'ID " + id + " n'existe pas.");
        }
    }

    private Collection<Integer> getAllEventId(){
        return em.createQuery("SELECT event.idEvent FROM Event event", Integer.class).getResultList();
    }

    private Donation getDonationById(int id) {
        try {
            return em.createQuery("SELECT d FROM Donation d WHERE donationId = :donationId", Donation.class)
                    .setParameter("donationId", id)
                    .getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            //throw new EventNotFoundException(alert.getAlertId());
            return null;
        }
    }

    private boolean isEventExists(int id) {
        Collection<Integer> allEventIds = getAllEventId();
        System.out.println(allEventIds.contains(id));
        return allEventIds.contains(id);
    }


}
