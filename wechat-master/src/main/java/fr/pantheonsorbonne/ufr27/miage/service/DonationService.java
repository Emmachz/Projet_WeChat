package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import jakarta.transaction.Transactional;

public interface DonationService {
    Donation createDonation(Donation donation) throws UnsuficientQuotaForVenueException;



    void cancelDonation(int donationId) throws UnsuficientQuotaForVenueException;

}
