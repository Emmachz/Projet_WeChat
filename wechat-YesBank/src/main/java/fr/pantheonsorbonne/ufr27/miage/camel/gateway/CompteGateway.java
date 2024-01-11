package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.service.OperationServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CompteGateway {

   @Inject
   OperationServiceImpl operationService;

    TransfertArgent realizeOperation (TransfertArgent transfertArgent) throws NoSuchComptException{
        return operationService.realizeOperation(transfertArgent);
    }
}
