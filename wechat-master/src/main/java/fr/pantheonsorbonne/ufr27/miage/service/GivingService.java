package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;

public interface GivingService {

    Giving giveMoney(Giving give) throws UnsuficientQuotaForVenueException;
    Giving giveTime(Giving give) throws UnsuficientQuotaForVenueException;
    Giving giveClothe(Giving give) throws UnsuficientQuotaForVenueException;
}