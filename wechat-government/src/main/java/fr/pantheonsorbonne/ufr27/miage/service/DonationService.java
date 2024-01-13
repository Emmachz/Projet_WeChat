package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.model.Region;

import java.util.Collection;

public interface DonationService {
    public Collection<Donation> getDonationService();
    public String addDonationService(Collection<String> regions, String RegionOfNeed, String description ) throws UnsuficientQuotaForVenueException;

    }