package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchAccountException;
import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientAmountInAccountException;
import fr.pantheonsorbonne.ufr27.miage.service.OperationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CompteGateway {

   @Inject
   OperationService operationService;

    public TransfertArgent updateTwoComptesBank(TransfertArgent transfertArgent){ return operationService.updateTwoComptesBank(transfertArgent);}
    public TransfertArgent updateCompteBankCredit(TransfertArgent transfertArgent){ return operationService.updateCompteBankCredit(transfertArgent);}
    public TransfertArgent updateCompteBankDebit(TransfertArgent transfertArgent){return operationService.updateCompteBankDebit(transfertArgent);}

    public BankOperation debit(BankOperation debitInfos)
    {
        try
        {
            operationService.debitMoney(debitInfos.getUserNumeroBank(), debitInfos.getAmount());
            debitInfos.setComplete(true);
        } catch (NoSuchAccountException | UnsufficientAmountInAccountException e)
        {
            debitInfos.setComplete(false);
        }
        return debitInfos;
    }

    public BankOperation credit(BankOperation creditInfos)
    {
        try
        {
            operationService.creditMoney(creditInfos.getUserNumeroBank(), creditInfos.getAmount());
            creditInfos.setComplete(true);
        } catch (NoSuchAccountException e)
        {
            creditInfos.setComplete(false);
        }
        return creditInfos;
    }

}
