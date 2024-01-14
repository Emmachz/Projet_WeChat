package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientWalletAmountToPay;

public interface WalletService {
    public TransfertArgent credit(TransfertArgent transfer);

    public PurchaseDTO debit(PurchaseDTO purchase) throws UnsufficientWalletAmountToPay;
}
