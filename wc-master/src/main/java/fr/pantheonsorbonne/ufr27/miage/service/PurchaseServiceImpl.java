package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.PurchaseDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchaseServiceImpl implements PurchaseService {

    @Inject
    PurchaseDAO purchaseDAO;

    public PurchaseDTO init(PurchaseDTO purchase)
    {
        this.purchaseDAO.createPurchase(
                purchase.getExternalSellerId(),
                purchase.getWeChatUserId(),
                purchase.getAmount()
        );

        return purchase;
    }
}
