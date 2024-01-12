package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Help;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;

import java.util.Collection;import java.util.List;

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
    public Donation getDonationId(int id) {
        try {
            return em.createQuery("SELECT donation FROM Donation donation WHERE donation.id = :id", Donation.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            //throw new DonationNotFoundException(alert.getAlertId());
            return null;
        }
    }

    @Override
    @Transactional
    public void addDonation(Donation donation) {
        if (!isDonationExists(donation.getId())) {
            Donation attachedDonation = em.merge(donation);
            em.persist(attachedDonation);
        } else {
            System.out.println("Erreur : L'événement avec l'ID " + donation.getId() + " existe déjà.");
        }
    }

    @Override
    @Transactional
    public Donation addDonation(int id, String description, List<Help> helps) {
        Donation newDonation = new Donation();
        newDonation.setId(id);
        newDonation.setDescription(description);
        newDonation.setHelps(helps);
        em.getTransaction().begin();

        // Réattacher l'entité à la session avant de la persister
        em.merge(newDonation);

        // Persister l'entité dans la base de données
        em.persist(newDonation);

        // Valider la transaction
        em.getTransaction().commit();

        System.out.println("la donation ajouté avec succès.");
        //em.persist(newDonation);
        System.out.println(newDonation);

        return newDonation;
    }

    @Override
    @Transactional
    public void deleteDonation(int id){
        Donation donationToDelete = getDonationId(id);
        if (donationToDelete != null) {
            em.remove(donationToDelete);
        } else {
            System.out.println("La donation avec l'ID " + id + " n'existe pas.");
        }
    }

    private Collection<Integer> getAllDonationId(){
        return em.createQuery("SELECT donation.id FROM Donation donation", Integer.class).getResultList();
    }
    private boolean isDonationExists(int id) {
        Collection<Integer> allDonationIds = getAllDonationId();
        System.out.println(allDonationIds.contains(id));
        return allDonationIds.contains(id);
    }


}