package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.service.OperationServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class CompteGateway {

    @Inject
    OperationServiceImpl operationService;

    public Giving realizeOperationGiving(Giving give) throws NoSuchComptException {
        return operationService.realizeOperationGiving(give);
    }
}
