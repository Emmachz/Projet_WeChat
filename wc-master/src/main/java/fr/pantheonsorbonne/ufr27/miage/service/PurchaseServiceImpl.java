package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.ExternalSellerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.PurchaseDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TransferDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.*;
import fr.pantheonsorbonne.ufr27.miage.model.ExternalSeller;
import fr.pantheonsorbonne.ufr27.miage.model.Purchase;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PurchaseServiceImpl implements PurchaseService {

    @Inject
    PurchaseDAO purchaseDAO;

    @Inject
    ExternalSellerDAO esDAO;

    @Inject
    UserDAO userDAO;


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
    @Transactional
    public BankOperation findBankDebitInfosFromPurchase(PurchaseDTO purchase) {
        try
        {
            User sender = this.userDAO.findUserByLogin(purchase.getLoginUser());
            return new BankOperation(sender.getUserNumeroBank(), sender.getUserNameBank(), purchase.getAmount());
        } catch (UserNotFoundException.NoExistUserException e)
        {
            throw new RuntimeException();
        }
    }

    @Override
    public BankOperation findBankCreditInfosFromPurchase(PurchaseDTO purchase) {
        try
        {
            ExternalSeller receiver = this.esDAO.findSellerByLogin(purchase.getLoginSeller());
            return new BankOperation(receiver.getSellerNumeroBank(), receiver.getSellerNameBank(), purchase.getAmount());
        } catch (SellerNotRegisteredException e)
        {
            throw new RuntimeException();
        }
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
