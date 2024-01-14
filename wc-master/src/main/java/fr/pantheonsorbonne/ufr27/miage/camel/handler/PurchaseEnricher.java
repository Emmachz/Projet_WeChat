package fr.pantheonsorbonne.ufr27.miage.camel.handler;

import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseConfirmation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PurchaseNotExistException;
import fr.pantheonsorbonne.ufr27.miage.service.PurchaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PurchaseEnricher {

    @Inject
    PurchaseService service;

    public PurchaseDTO findPurchaseInfosFromConfirmation(PurchaseConfirmation confirmation) throws PurchaseNotExistException
    {
        return this.service.findPurchase(confirmation.getPurchaseId());
    }

    public PurchaseDTO findPurchaseInfosFromBankOperation(BankOperation operation) throws PurchaseNotExistException {
        return this.service.findPurchase(operation.getWeChatTransferId());
    }
}
