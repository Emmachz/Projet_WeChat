package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.PurchaseDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.*;
import fr.pantheonsorbonne.ufr27.miage.model.Purchase;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchaseServiceImpl implements PurchaseService {

    @Inject
    PurchaseDAO purchaseDAO;

    public PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotExistingException
    {
        this.purchaseDAO.createPurchase(
                purchase.getExternalSellerId(),
                purchase.getWeChatUserId(),
                purchase.getAmount()
        );

        return purchase;
    }

    @Override
    public void confirm(PurchaseConfirmation confirmationInfos) throws PurchaseNotExistException, UserNotAllowedToPayException {
        Purchase purchase = this.purchaseDAO.findPurchase(confirmationInfos.getPurchaseId());
        User purchaser = purchase.getIdWC();
        if(!purchaser.getUserEmail().equals(confirmationInfos.getUserMail()))
        {
            throw new UserNotAllowedToPayException();
        }
        this.purchaseDAO.confirmPurchase(confirmationInfos.getPurchaseId());
    }

    @Override
    public PurchaseDTO findPurchase(int id) throws PurchaseNotExistException {
        return convertPurchaseEntityToDTO(this.purchaseDAO.findPurchase(id));
    }

    private PurchaseDTO convertPurchaseEntityToDTO(Purchase purchase)
    {
        return new PurchaseDTO(
                (int)Long.parseLong(purchase.getIdES().getEsId().toString()),
                (int)Long.parseLong(purchase.getIdWC().getUserId().toString()),
                purchase.getAmount()
        );
    }
}
