package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import jakarta.transaction.Transactional;

public interface DonationDAO {

    void addDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donationDTO);
}
