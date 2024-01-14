package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.service.VersementService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BankConverter {

    @Inject
    VersementService service;

    public BankOperation convertToDebitOperation(PurchaseDTO purchase) {
        BankOperation debitInfos = service.findBankDebitInfosFromPurchase(purchase);
        debitInfos.setWeChatTransferId(purchase.getPurchaseId());
        return debitInfos;
    }

    public BankOperation convertToCreditOperation(PurchaseDTO purchase) {
        BankOperation creditInfos = service.findBankCreditInfosFromPurchase(purchase);
        creditInfos.setWeChatTransferId(purchase.getPurchaseId());
        return creditInfos;
    }
}
