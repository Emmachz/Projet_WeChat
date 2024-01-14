package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExternalSeller;

public interface ExternalSellerDAO {
    ExternalSeller findSellerByLogin(String login) throws SellerNotRegisteredException;
}
