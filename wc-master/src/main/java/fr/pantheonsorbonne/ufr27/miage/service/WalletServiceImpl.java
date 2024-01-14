package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientWalletAmountToPay;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WalletServiceImpl implements WalletService{
    @Inject
    UserDAO dao;

    @Override
    public TransfertArgent credit(TransfertArgent transfer) {
        dao.creditAmountToUser(transfer.getLoginReceveur(), transfer.getValue());
        transfer.setCreditOk(true);
        return transfer;
    }

    @Override
    public PurchaseDTO debit(PurchaseDTO purchase) throws UnsufficientWalletAmountToPay {
        try{
            dao.debitAmountToUser(purchase.getLoginUser(), purchase.getAmount());
            purchase.setDebitOk(true);
        }catch (UnsufficientWalletAmountToPay e)
        {
            purchase.setDebitOk(false);
        }
        return purchase;
    }
}
