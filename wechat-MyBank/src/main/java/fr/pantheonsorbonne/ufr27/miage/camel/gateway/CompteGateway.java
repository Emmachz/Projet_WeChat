package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.service.OperationServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CompteGateway {

   @Inject
   OperationServiceImpl operationService;

    public TransfertArgent updateTwoComptesBank(TransfertArgent transfertArgent){ return operationService.updateTwoComptesBank(transfertArgent);}
    public TransfertArgent updateCompteBankCredit(TransfertArgent transfertArgent){ return operationService.updateCompteBankCredit(transfertArgent);}
    public TransfertArgent updateCompteBankDebit(TransfertArgent transfertArgent){return operationService.updateCompteBankDebit(transfertArgent);}

}
