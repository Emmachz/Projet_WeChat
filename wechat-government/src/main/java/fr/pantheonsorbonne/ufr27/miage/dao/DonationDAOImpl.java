package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Help;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Random;

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
    public Donation addDonation(List<Region> regions, Region RegionOfNeed, String description) throws UnsuficientQuotaForVenueException {

        Donation donation = new Donation();
        donation.setDescription(description);


        for (int i = 0; i < regions.size(); i++) {
                int randomIndexMoney = new Random().nextInt(100000);
                int randomIndexTime = new Random().nextInt(100000);
                int randomIndexClothe = new Random().nextInt(100000);
                Help help = new Help();
                help.setRegion(regions.get(i).getRegion());
                help.setMoneySupport(randomIndexMoney);
                help.setTimeSupport(randomIndexTime);
                help.setClotheSupport(randomIndexClothe);
                em.persist(help);
                donation.getHelps().add(help);

        }

        Help helpForRegionNeed = new Help();
        int randomIndexTime = new Random().nextInt(100000);
        int randomIndexClothe = new Random().nextInt(100000);
        helpForRegionNeed.setRegion(RegionOfNeed.getRegion());
        helpForRegionNeed.setMoneySupport(0);
        helpForRegionNeed.setTimeSupport(randomIndexTime);
        helpForRegionNeed.setClotheSupport(randomIndexClothe);
        em.persist(helpForRegionNeed);
        donation.getHelps().add(helpForRegionNeed);


        // Réattacher l'entité à la session avant de la persister
        em.merge(donation);

        // Persister l'entité dans la base de données
        em.persist(donation);

        // Valider la transaction
        em.getTransaction().commit();

        System.out.println("La donation a été ajouté avec succès.");
        //em.persist(newEvent);
        System.out.println(donation);

        return donation;

    }


/*
    @Override
    @Transactional
    public void deleteDonation(int id){
        Event eventToDelete = getEventId(id);
        if (eventToDelete != null) {
            em.remove(eventToDelete);
        } else {
            System.out.println("L'événement avec l'ID " + id + " n'existe pas.");
        }
    }

    private Collection<Integer> getAllEventId(){
        return em.createQuery("SELECT event.idEvent FROM Event event", Integer.class).getResultList();
    }

    private boolean isEventExists(int id) {
        Collection<Integer> allEventIds = getAllEventId();
        System.out.println(allEventIds.contains(id));
        return allEventIds.contains(id);
    }
*/

}
