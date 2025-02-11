package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchAccountException;
import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientAmountInAccountException;
import fr.pantheonsorbonne.ufr27.miage.service.OperationServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CompteGateway {

   @Inject
   OperationServiceImpl operationService;


    public Giving realizeOperationGiving(Giving give) throws NoSuchAccountException {
        return operationService.realizeOperationGiving(give);
    }

    TransfertArgent realizeOperation (TransfertArgent transfertArgent) throws NoSuchAccountException {
        return operationService.realizeOperation(transfertArgent);
    }

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
