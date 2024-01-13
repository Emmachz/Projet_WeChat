package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaDonationException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;

public interface GivingService {

    Giving giveMoney(Giving give) throws UnsuficientQuotaDonationException;
    Giving giveTime(Giving give) throws UnsuficientQuotaDonationException;
    Giving giveClothe(Giving give)throws UnsuficientQuotaDonationException;
    Giving convertirGiving(Giving give) throws UserNotFoundException.NoExistUserException;
}