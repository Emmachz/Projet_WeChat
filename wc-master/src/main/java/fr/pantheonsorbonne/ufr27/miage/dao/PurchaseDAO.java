package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.AlreadyPaidPurchaseException;
import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotExistingException;
import fr.pantheonsorbonne.ufr27.miage.model.Purchase;

public interface PurchaseDAO {
    public Purchase createPurchase(String externalSeller, String weChatUser, double amount) throws SellerNotRegisteredException, UserNotExistingException;

    public void confirmPurchase(Long id) throws PurchaseNotExistException, AlreadyPaidPurchaseException;

    public Purchase findPurchase(Long id) throws PurchaseNotExistException;
}
