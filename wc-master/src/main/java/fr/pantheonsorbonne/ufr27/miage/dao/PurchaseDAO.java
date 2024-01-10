package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotExistingException;
import fr.pantheonsorbonne.ufr27.miage.model.Purchase;

public interface PurchaseDAO {
    public void createPurchase(int idES, int idWC, int amount) throws SellerNotRegisteredException, UserNotExistingException;

    public void confirmPurchase(int id) throws PurchaseNotExistException;

    public Purchase findPurchase(int id) throws PurchaseNotExistException;
}
