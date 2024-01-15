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

    @Override
    @Transactional
    public void addDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donationDTO) {

        Donation donation = new Donation();
        donation.setDescription(donationDTO.getDescription());
        donation.setRegionOfNeed(donationDTO.getRegionOfNeed());
        donation.setMoneySupport(donationDTO.getMoneySupport());
        donation.setTimeSupport(donationDTO.getTimeSupport());
        donation.setClotheSupport(donationDTO.getClotheSupport());
        em.persist(donation);

    }

}