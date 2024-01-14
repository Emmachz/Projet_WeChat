package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DonationDAOImpl implements DonationDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addDonation(Donation donation) {
        em.merge(donation);
    }

}