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

    @Override
    public PurchaseDTO init(PurchaseDTO purchase) throws SellerNotRegisteredException, UserNotExistingException
    {
        Purchase entity = this.purchaseDAO.createPurchase(
                purchase.getLoginSeller(),
                purchase.getLoginUser(),
                purchase.getAmount()
        );

        purchase.setPurchaseId(entity.getId());

        return purchase;
    }

    @Override
    public void confirm(PurchaseConfirmation confirmationInfos) throws PurchaseNotExistException, UserNotAllowedToPayException, AlreadyPaidPurchaseException {
        Purchase purchase = this.purchaseDAO.findPurchase(confirmationInfos.getPurchaseId());
        User purchaser = purchase.getUser();
        if(!purchaser.getUserLogin().equals(confirmationInfos.getUserLogin()))
        {
            throw new UserNotAllowedToPayException();
        }
        this.purchaseDAO.confirmPurchase(confirmationInfos.getPurchaseId());
    }

    @Override
    public PurchaseDTO findPurchase(Long id) throws PurchaseNotExistException {
        return convertPurchaseEntityToDTO(this.purchaseDAO.findPurchase(id));
    }

    private PurchaseDTO convertPurchaseEntityToDTO(Purchase purchase)
    {
        PurchaseDTO dto = new PurchaseDTO(
                purchase.getUser().getUserLogin(),
                purchase.getExternalSeller().getLoginSeller(),
                purchase.getAmount()
        );

        dto.setPurchaseId(purchase.getId());

        return dto;
    }
}
